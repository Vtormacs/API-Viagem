package com.ViViagens.API.viagem;

import com.ViViagens.API.atividade.AtividadesServisos;
import com.ViViagens.API.atividade.DadosDaAtividade;
import com.ViViagens.API.atividade.RespostaAtividade;
import com.ViViagens.API.atividade.SolicitacaoAtividade;
import com.ViViagens.API.link.DadosDoLink;
import com.ViViagens.API.link.LinkServicos;
import com.ViViagens.API.link.RespostaLink;
import com.ViViagens.API.link.SolicitacaoLink;
import com.ViViagens.API.participante.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/viagens")
public class ControladorViagem {

    @Autowired
    private ParticipantesServicos participantesServicos;

    @Autowired
    private AtividadesServisos atividadesServisos;

    @Autowired
    private LinkServicos linkServicos;

    @Autowired
    private RepositorioViagem repositorio;

    //VIAGEM
    @PostMapping
    public ResponseEntity<RespostaCriacaoViagem> criaViagem(@RequestBody SolicitacaoViagem payload) {
        Viagem newViagem = new Viagem(payload);

        this.repositorio.save(newViagem);

        this.participantesServicos.registrarParticipantesEvento(payload.emails_convite(), newViagem);

        return ResponseEntity.ok(new RespostaCriacaoViagem(newViagem.getId()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Viagem> detalhesViagem(@PathVariable UUID id) {
        Optional<Viagem> viagem = this.repositorio.findById(id);

        return viagem.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Viagem> atualizarViagem(@PathVariable UUID id, @RequestBody SolicitacaoViagem payload) {
        Optional<Viagem> viagem = this.repositorio.findById(id);

        if (viagem.isPresent()) {
            Viagem viagemCrua = viagem.get();
            viagemCrua.setFim(LocalDateTime.parse(payload.fim(), DateTimeFormatter.ISO_DATE_TIME));
            viagemCrua.setInicio(LocalDateTime.parse(payload.inicio(), DateTimeFormatter.ISO_DATE_TIME));
            viagemCrua.setDestino(payload.destino());

            this.repositorio.save(viagemCrua);

            return ResponseEntity.ok(viagemCrua);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/confirmacao")
    public ResponseEntity<Viagem> confirmarViagem(@PathVariable UUID id) {
        Optional<Viagem> viagem = this.repositorio.findById(id);

        if (viagem.isPresent()) {
            Viagem viagemCrua = viagem.get();
            viagemCrua.setConfirmacao(true);

            this.repositorio.save(viagemCrua);
            this.participantesServicos.acionadorConfirmacaoEmailParticipantes(id);

            return ResponseEntity.ok(viagemCrua);
        }

        return ResponseEntity.notFound().build();
    }

    //PARTICIPANTE
    @PostMapping("/{id}/convite")
    public ResponseEntity<RespostaCriacaoParticipante> convidarParticipante(@PathVariable UUID id, @RequestBody SolicitacaoParticipante payload) {
        Optional<Viagem> viagem = this.repositorio.findById(id);

        if (viagem.isPresent()) {
            Viagem viagemCrua = viagem.get();


            RespostaCriacaoParticipante respostaParticipante = this.participantesServicos.registrarParticipanteViagem(payload.email(),viagemCrua);

            if(viagemCrua.getConfirmacao()) this.participantesServicos.acionadorConfirmacaoEmailParticipante(payload.email());

            return ResponseEntity.ok(respostaParticipante);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/participante")
    public ResponseEntity<List<DadosDoParticipante>> pegarTodosParticipantes (@PathVariable UUID id){

        List<DadosDoParticipante> listaParticipantes = this.participantesServicos.pegarTodosParticipantesEvento(id);

       return ResponseEntity.ok(listaParticipantes);
    }

    //ATIVIDADE
    @PostMapping("/{id}/atividades")
    public ResponseEntity< RespostaAtividade> registrarAtividade(@PathVariable UUID id, @RequestBody SolicitacaoAtividade payload) {
        Optional<Viagem> viagem = this.repositorio.findById(id);

        if (viagem.isPresent()) {
            Viagem viagemCrua = viagem.get();

            RespostaAtividade respostaAtividade = this.atividadesServisos.registrarAtividade(payload, viagemCrua);

            return ResponseEntity.ok(respostaAtividade);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/atividades")
    public ResponseEntity<List<DadosDaAtividade>> pegarTodasAtividades (@PathVariable UUID id){

        List<DadosDaAtividade> listaAtividades = this.atividadesServisos.pegarTodasAtividades(id);

        return ResponseEntity.ok(listaAtividades);
    }

    //LINK
    @PostMapping("/{id}/links")
    public ResponseEntity<RespostaLink> registrarLink(@PathVariable UUID id, @RequestBody SolicitacaoLink payload) {
        Optional<Viagem> viagem = this.repositorio.findById(id);

        if (viagem.isPresent()) {
            Viagem viagemCrua = viagem.get();

            RespostaLink respostaLink = this.linkServicos.registrarLink(payload, viagemCrua);

            return ResponseEntity.ok(respostaLink);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/links")
    public ResponseEntity<List<DadosDoLink>> pegarTodosLinks (@PathVariable UUID id){

        List<DadosDoLink> listaLinks = this.linkServicos.pegarTodosLinks(id);

        return ResponseEntity.ok(listaLinks);
    }
}

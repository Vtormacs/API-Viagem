package com.ViViagens.API.participante;

import com.ViViagens.API.viagem.Viagem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ParticipantesServicos {

    @Autowired
    private RepositorioParticipantes repositorio;

    public void registrarParticipantesEvento(List<String> participantesConvite, Viagem viagem) {

        List<Participante> participantes = participantesConvite.stream().map(email -> new Participante(email, viagem)).toList();

        this.repositorio.saveAll(participantes);

        System.out.println(participantes.get(0).getId());
    }

    public RespostaCriacaoParticipante registrarParticipanteViagem(String email, Viagem viagem){
        Participante novoParicipante = new Participante(email, viagem);

        this.repositorio.save(novoParicipante);

        return new RespostaCriacaoParticipante(novoParicipante.getId());
    }

    public void acionadorConfirmacaoEmailParticipantes(UUID viagemId) {
        // Implementação do método de confirmação por email
    }

    public void acionadorConfirmacaoEmailParticipante(String email) {
        // Implementação do método de confirmação por email
    }

    public List<DadosDoParticipante> pegarTodosParticipantesEvento(UUID viagemId){

        return this.repositorio.findByViagemId(viagemId).stream().map(participante -> new DadosDoParticipante(participante.getId(), participante.getNome(), participante.getEmail(), participante.getConfirmacao())).toList();
    }
}

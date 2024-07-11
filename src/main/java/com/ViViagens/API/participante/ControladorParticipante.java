package com.ViViagens.API.participante;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/participante")
public class ControladorParticipante {

    @Autowired
    private RepositorioParticipantes repositorio;

    @PostMapping("/{id}/confirmacao")
    public ResponseEntity<Participante> confirmarParticipante(@PathVariable UUID id, @RequestBody SolicitacaoParticipante payload){
        Optional<Participante> participante = this.repositorio.findById(id);

        if(participante.isPresent()){
            Participante participanteCru = participante.get();
            participanteCru.setConfirmacao(true);
            participanteCru.setNome(payload.nome());

            this.repositorio.save(participanteCru);

            return ResponseEntity.ok(participanteCru);
        }
        return  ResponseEntity.notFound().build();
    }

}

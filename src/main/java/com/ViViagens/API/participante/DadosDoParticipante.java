package com.ViViagens.API.participante;

import java.util.UUID;

public record DadosDoParticipante(UUID id, String nome, String email, Boolean confirmacao) {
}

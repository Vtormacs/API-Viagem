package com.ViViagens.API.atividade;

import java.time.LocalDateTime;
import java.util.UUID;

public record DadosDaAtividade(UUID id, String titulo, LocalDateTime data) {
}

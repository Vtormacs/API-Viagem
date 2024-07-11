package com.ViViagens.API.viagem;

import java.util.List;

public record SolicitacaoViagem(String destino, String inicio, String fim, List<String> emails_convite, String email_dono_da_viagem, String nome_dono_da_viagem ) {
}

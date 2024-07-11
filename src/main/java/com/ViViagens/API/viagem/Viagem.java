package com.ViViagens.API.viagem;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Entity
@Table(name = "viagens")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Viagem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String destino;

    @Column(name = "inicio", nullable = false)
    private LocalDateTime inicio;

    @Column(name = "fim", nullable = false)
    private LocalDateTime fim;

    @Column(name = "confirmacao", nullable = false)
    private Boolean confirmacao;

    @Column(name = "nome_dono_da_viagem", nullable = false)
    private String nomeDonoViagem;

    @Column(name = "email_dono_da_viagem", nullable = false)
    private String emailDonoViagem;

    public Viagem(SolicitacaoViagem data){

        this.destino = data.destino();
        this.confirmacao = false;
        this.emailDonoViagem = data.email_dono_da_viagem();
        this.nomeDonoViagem = data.nome_dono_da_viagem();
        this.inicio = LocalDateTime.parse(data.inicio(), DateTimeFormatter.ISO_DATE_TIME);
        this.fim = LocalDateTime.parse(data.fim(), DateTimeFormatter.ISO_DATE_TIME);

    }
}

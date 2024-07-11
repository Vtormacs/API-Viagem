package com.ViViagens.API.atividade;

import com.ViViagens.API.viagem.Viagem;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Entity
@Table(name = "atividades")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Atividade {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private LocalDateTime data;

    @Column(nullable = false)
    private String titulo;

    @ManyToOne
    @JoinColumn(name = "viagem_id", nullable = false)
    private Viagem viagem;

    public Atividade(String titulo, String data, Viagem viagem){

        this.titulo = titulo;
        this.data = LocalDateTime.parse(data, DateTimeFormatter.ISO_DATE_TIME);
        this.viagem = viagem;

    }
}

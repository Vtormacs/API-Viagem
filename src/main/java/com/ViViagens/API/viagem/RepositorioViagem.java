package com.ViViagens.API.viagem;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RepositorioViagem extends JpaRepository<Viagem, UUID> {
}

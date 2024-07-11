package com.ViViagens.API.participante;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface RepositorioParticipantes extends JpaRepository<Participante, UUID> {
    List<Participante> findByViagemId(UUID viagemId);
}

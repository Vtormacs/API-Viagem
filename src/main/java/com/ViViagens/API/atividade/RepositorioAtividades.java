package com.ViViagens.API.atividade;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface RepositorioAtividades extends JpaRepository <Atividade, UUID>{
    List<Atividade> findByViagemId(UUID viagemId);
}

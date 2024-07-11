package com.ViViagens.API.link;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface RepositorioLink extends JpaRepository<Link, UUID> {
    public List<Link> findByViagemId(UUID viagemId);
}

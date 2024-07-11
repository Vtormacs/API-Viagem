package com.ViViagens.API.link;

import com.ViViagens.API.atividade.DadosDaAtividade;
import com.ViViagens.API.viagem.Viagem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service

public class LinkServicos {
    @Autowired
    private RepositorioLink repositorio;

    public RespostaLink registrarLink(SolicitacaoLink payload, Viagem viagem){
        Link novoLink = new Link(payload.titulo(), payload.url(), viagem);

        this.repositorio.save(novoLink);

        return new RespostaLink(novoLink.getId());
    }

    public List<DadosDoLink> pegarTodosLinks(UUID viagemId){

        return this.repositorio.findByViagemId(viagemId).stream().map(link -> new DadosDoLink(link.getId(), link.getTitulo(), link.getUrl())).toList();
    }
}

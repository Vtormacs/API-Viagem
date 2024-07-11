package com.ViViagens.API.atividade;

import com.ViViagens.API.viagem.Viagem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AtividadesServisos {

    @Autowired
    private RepositorioAtividades repositorio;

    public RespostaAtividade registrarAtividade(SolicitacaoAtividade payload, Viagem viagem){
        Atividade novaAtividade = new Atividade(payload.titulo(), payload.data(), viagem);

        this.repositorio.save(novaAtividade);

        return new RespostaAtividade(novaAtividade.getId());
    }

    public List<DadosDaAtividade> pegarTodasAtividades(UUID viagemId){

        return this.repositorio.findByViagemId(viagemId).stream().map(atividade -> new DadosDaAtividade(atividade.getId(), atividade.getTitulo(), atividade.getData())).toList();
    }
}

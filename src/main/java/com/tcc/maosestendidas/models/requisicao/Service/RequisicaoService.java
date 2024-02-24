package com.tcc.maosestendidas.models.requisicao.Service;

import com.tcc.maosestendidas.models.requisicao.DTO.RequisicaoDTO;
import com.tcc.maosestendidas.models.requisicao.Entity.Requisicao;
import com.tcc.maosestendidas.models.requisicao.Entity.StatusRequisicao;

import java.util.List;

public interface RequisicaoService {
    List<Requisicao> listarRequisicoes();

    Requisicao buscarRequisicaoPeloId(String idRequisicao);

    List<Requisicao> buscarRequisicaoPelaPessoa(String idPessoa);

    List<Requisicao> buscarRequisicaoPeloStatus(StatusRequisicao statusRequisicao);

    Requisicao criaRequisicao(RequisicaoDTO dto);

    Requisicao updateRequisicao(RequisicaoDTO dto, String idRequisicao);

    void deleteRequisicao(String idRequisicao);

}

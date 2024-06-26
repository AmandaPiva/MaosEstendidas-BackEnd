package com.tcc.maosestendidas.models.doacao.Service;

import com.tcc.maosestendidas.models.doacao.DTO.DoacaoDTO;
import com.tcc.maosestendidas.models.doacao.DTO.VinculaDoacaoNaRequisicaoDTO;
import com.tcc.maosestendidas.models.doacao.entity.Doacao;
import com.tcc.maosestendidas.models.doacao.entity.StatusDoacao;
import com.tcc.maosestendidas.models.requisicao.Entity.Requisicao;

import java.util.List;

public interface DoacaoService {
    List<Doacao> listarDoacoes();

    Doacao buscarDoacaoPeloId(String idDoacao);

    List<Doacao> buscarDoacaoPelaPessoa(String emailPessoa);

//    List<Doacao> buscarDoacaoPeloStatus(StatusDoacao statusDoacao);

    Doacao criaDoacao(DoacaoDTO dto);

    Requisicao vinculaDoacaoNaRequisicao(VinculaDoacaoNaRequisicaoDTO dto);



}

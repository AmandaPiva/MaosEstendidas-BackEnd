package com.tcc.maosestendidas.models.doacao.Service;

import com.tcc.maosestendidas.models.doacao.DTO.DoacaoDTO;
import com.tcc.maosestendidas.models.doacao.entity.Doacao;
import com.tcc.maosestendidas.models.doacao.entity.StatusDoacao;

import java.util.List;

public interface DoacaoService {
    List<Doacao> listarDoacoes();

    Doacao buscarDoacaoPeloId(String idDoacao);

    List<Doacao> buscarDoacaoPelaPessoa(String idPessoa);

    List<Doacao> buscarDoacaoPeloStatus(StatusDoacao statusDoacao);

    Doacao criaDoacao(DoacaoDTO dto);

    Doacao updateStatusDoacao(String idDoacao, StatusDoacao novoStatus);

    Doacao updateDoacao(DoacaoDTO dto, String idDoacao);


}

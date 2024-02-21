package com.tcc.maosestendidas.models.doacoes.Service;

import com.tcc.maosestendidas.models.doacoes.DTO.DoacoesDTO;
import com.tcc.maosestendidas.models.doacoes.entity.Doacoes;

import java.util.List;
import java.util.Optional;

public interface DoacoesService {
    List<Doacoes> listarDoacoes();

    Doacoes buscarDoacaoPeloId(String idDoacao);

    List<Doacoes> buscarDoacaoPelaPessoa(String idPessoa);

    Doacoes criaDoacao(DoacoesDTO dto);

    Doacoes updateDoacao(DoacoesDTO dto, String idDoacao);


}

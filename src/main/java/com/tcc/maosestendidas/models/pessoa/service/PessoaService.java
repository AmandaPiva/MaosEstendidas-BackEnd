package com.tcc.maosestendidas.models.pessoa.service;

import com.tcc.maosestendidas.models.pessoa.DTO.PessoaDTO;
import com.tcc.maosestendidas.models.pessoa.entity.Pessoa;

import java.util.List;

public interface PessoaService {
    List<Pessoa> listaPessoas();

    Pessoa buscaPessoaPeloEmail(String emailPessoa);

    Pessoa buscaPessoaPeloCpf(String cpfPessoa);

    Pessoa criaPessoa(PessoaDTO dto);

    Pessoa updatePessoa(PessoaDTO dto, String idPessoa);
}

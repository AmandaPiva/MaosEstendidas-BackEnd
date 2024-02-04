package com.tcc.maosestendidas.models.pessoa.service;

import com.tcc.maosestendidas.models.pessoa.DTO.PessoaDTO;
import com.tcc.maosestendidas.models.pessoa.entity.Pessoa;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface PessoaService {
    List<Pessoa> listaPessoas();

    Pessoa buscaPessoaPeloEmail(String emailPessoa);

    Pessoa buscaPessoaPeloCpf(String cpfPessoa);

    Pessoa criaPessoa(PessoaDTO dto);

    Pessoa updatePessoa(PessoaDTO dto, String idPessoa);

    Boolean comparaSenhas(String senhaCriptografada, String senha);

    UserDetailsService userDetailsService();
}

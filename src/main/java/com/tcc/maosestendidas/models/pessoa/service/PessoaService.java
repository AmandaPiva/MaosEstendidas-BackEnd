package com.tcc.maosestendidas.models.pessoa.service;

import com.tcc.maosestendidas.models.pessoa.DTO.PessoaDTO;
import com.tcc.maosestendidas.models.pessoa.entity.Pessoa;
import com.tcc.maosestendidas.models.pessoa.entity.PessoaRole;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface PessoaService {
    List<Pessoa> listaPessoas();

    Pessoa buscaPessoaPeloEmail(String emailPessoa);

    Optional<Pessoa> buscaPessoaPeloEmailOptional(String email);


    Pessoa buscaPessoaPeloDocumento(String documentoPessoa);

    Pessoa buscaPessoaPeloCelular(String celular);

    Pessoa criaPessoa(PessoaDTO dto);

    Pessoa criaPessoa(Pessoa pessoa);

    Pessoa updatePessoa(PessoaDTO dto, String idPessoa);

    Pessoa updateSenha(String senha, String email);

    List<Pessoa> listarPessoasPelaRole(String rolePessoa);

    Boolean comparaSenhas(String senhaCriptografada, String senha);

    UserDetailsService userDetailsService();
}

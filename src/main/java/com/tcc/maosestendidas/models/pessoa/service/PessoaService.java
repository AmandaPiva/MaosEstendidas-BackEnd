package com.tcc.maosestendidas.models.pessoa.service;

import com.tcc.maosestendidas.models.pessoa.DTO.PessoaDTO;
import com.tcc.maosestendidas.models.pessoa.entity.Pessoa;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface PessoaService {
    List<Pessoa> listaPessoas();

    Pessoa buscaPessoaPeloEmail(String emailPessoa);

    Optional<Pessoa> buscaPessoaPeloEmailOptional(String email);

//    Pessoa uploadImagem(String idPessoa, MultipartFile imagem) throws IOException;

    byte[] getImagemByIdPessoa(String idPessoa);

    Pessoa buscaPessoaPeloDocumento(String documentoPessoa);

    Pessoa buscaPessoaPeloCelular(String celular);

    Pessoa criaPessoa(PessoaDTO dto);

    Pessoa criaPessoa(Pessoa pessoa);

    Pessoa updatePessoa(PessoaDTO dto, String idPessoa);

    Pessoa updateSenha(String senha, String email);

    Pessoa updateSenhaManual(PessoaDTO dto, String idPessoa);

    List<Pessoa> listarPessoasPelaRole(String rolePessoa);

    Boolean comparaSenhas(String senhaCriptografada, String senha);

    UserDetailsService userDetailsService();
}

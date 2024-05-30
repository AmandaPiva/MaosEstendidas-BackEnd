package com.tcc.maosestendidas.models.pessoa.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa, String> {
    @Override
    Optional<Pessoa> findById(String id);

    Optional<Pessoa> findByNomePessoa(String nomePessoa);

    Optional<Pessoa> findByEmailPessoa(String emailPessoa);

    Optional<Pessoa> findByDocumentoPessoa(String documentoPessoa);

    Optional<Pessoa> findByCelular(String celularPessoa);

    List<Pessoa> findByRolePessoa(PessoaRole pessoaRole);

    Optional<Pessoa> findBySenhaPessoa(String senha);

}

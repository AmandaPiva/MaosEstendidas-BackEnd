package com.tcc.maosestendidas.models;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa, String> {
    @Override
    Optional<Pessoa> findById(String id);

    Optional<Pessoa> findByNomePessoa(String nomePessoa);

    Optional<Pessoa> findByEmailPessoa(String emailPessoa);
}

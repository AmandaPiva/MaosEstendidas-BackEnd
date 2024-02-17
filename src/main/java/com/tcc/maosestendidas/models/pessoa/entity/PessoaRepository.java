package com.tcc.maosestendidas.models.pessoa.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa, String> {
    @Override
    Optional<Pessoa> findById(String id);

    Optional<Pessoa> findByNomePessoa(String nomePessoa);

    Optional<Pessoa> findByEmailPessoa(String emailPessoa);

    Optional<Pessoa> findByCpfPessoa(String cpfPessoa);

    Optional<Pessoa> findBySenhaPessoa(String senha);
}

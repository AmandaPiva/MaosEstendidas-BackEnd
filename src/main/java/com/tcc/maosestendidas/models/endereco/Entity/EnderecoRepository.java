package com.tcc.maosestendidas.models.endereco.Entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EnderecoRepository extends JpaRepository<Endereco, String> {
    @Override
    Optional<Endereco> findById(String idEndereco);

    Optional<Endereco> findByLogradouro(String logradouro);

    Optional<Endereco> findByBairro(String bairro);

    Optional<Endereco> findByCidade(String cidade);

    Optional<Endereco> findByCep(String cep);


}

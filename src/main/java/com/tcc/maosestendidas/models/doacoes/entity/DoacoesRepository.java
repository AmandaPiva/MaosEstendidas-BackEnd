package com.tcc.maosestendidas.models.doacoes.entity;

import com.tcc.maosestendidas.models.pessoa.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DoacoesRepository extends JpaRepository<Doacoes, String> {
    Optional<Doacoes> findById(String idDoacoes);

    List<Doacoes> findByPessoaDoadora(Pessoa pessoaDoadora);

}

package com.tcc.maosestendidas.models.doacao.entity;

import com.tcc.maosestendidas.models.pessoa.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DoacaoRepository extends JpaRepository<Doacao, String> {
    Optional<Doacao> findById(String idDoacao);

    List<Doacao> findByPessoaDoadora(Pessoa pessoaDoadora);

}

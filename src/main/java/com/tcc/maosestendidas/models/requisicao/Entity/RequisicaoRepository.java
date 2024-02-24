package com.tcc.maosestendidas.models.requisicao.Entity;

import com.tcc.maosestendidas.models.pessoa.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RequisicaoRepository extends JpaRepository<Requisicao, String> {
    Optional<Requisicao> findById(String idRequisicao);

    List<Requisicao> findByPessoaDonataria(Pessoa pessoaDonataria);

    List<Requisicao> findByStatusRequisicao(StatusRequisicao statusRequisicao);
}

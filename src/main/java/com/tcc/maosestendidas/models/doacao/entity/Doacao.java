package com.tcc.maosestendidas.models.doacao.entity;

import com.tcc.maosestendidas.models.pessoa.entity.Pessoa;
import com.tcc.maosestendidas.models.requisicao.Entity.Requisicao;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
public class Doacao {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String idDoacao;

    //A TEBELA PESSOA TERÁ A ROLE SE É DOADOR OU DONATÁRIO
    @ManyToOne
    @JoinColumn(name = "idPessoa")
    private Pessoa pessoaDoadora;

   @ManyToOne
   @JoinColumn(name = "idRequisicao")
   private Requisicao requisicao;

    private String descricao;

    private LocalDateTime dataDoacao;

    @Enumerated(EnumType.STRING)
    private StatusDoacao statusDoacao;

}

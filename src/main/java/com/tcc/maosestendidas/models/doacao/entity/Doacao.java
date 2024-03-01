package com.tcc.maosestendidas.models.doacao.entity;

import com.tcc.maosestendidas.models.pessoa.entity.Pessoa;
import jakarta.persistence.*;
import lombok.Data;

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

    private String descricao;

    @Enumerated(EnumType.STRING)
    private StatusDoacao statusDoacao;

}

package com.tcc.maosestendidas.models.doacoes.entity;

import com.tcc.maosestendidas.models.pessoa.entity.Pessoa;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Doacoes {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String idDoacao;

    //A TEBELA PESSOA TERÁ A ROLE SE É DOADOR OU DONATÁRIO
    @ManyToOne
    @JoinColumn(name = "idPessoa")
    private Pessoa pessoaDoadora;

    private String descricao;

}

package com.tcc.maosestendidas.models.pessoa.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
public class PessoaRole {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String idPessoaRole;

    private String rolePessoa;

    @OneToMany
    private Set<Pessoa> pessoas;
}

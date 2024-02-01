package com.tcc.maosestendidas.models.pessoa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class PessoaRole {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String idPessoaRole;

    private String rolePessoa;


}

package com.tcc.maosestendidas.models.pessoa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String idPessoa;

    private String nomePessoa;
    private String emailPessoa;

    private String cpfPessoa;

    private Date dataNascimentoPessoa;



}

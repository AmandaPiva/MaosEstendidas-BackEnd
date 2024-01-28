package com.tcc.maosestendidas.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String idPessoa;

    private String nomePessoa;
    private String emailPessoa;

    private String cpfPessoa;

    private Date dataNascimentoPessoa;



}

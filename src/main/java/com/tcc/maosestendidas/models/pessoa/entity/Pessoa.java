package com.tcc.maosestendidas.models.pessoa.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
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

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dataNascimentoPessoa;
}

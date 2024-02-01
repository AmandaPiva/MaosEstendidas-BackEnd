package com.tcc.maosestendidas.models.pessoa.DTO;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class PessoaDTO {
    private String nomePessoa;
    private String emailPessoa;
    private String cpfPessoa;
    private LocalDate dataNascimentoPessoa;

}

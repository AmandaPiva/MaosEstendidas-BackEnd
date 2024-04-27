package com.tcc.maosestendidas.models.pessoa.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PessoaDTO {
    private String nomePessoa;
    private String emailPessoa;
    private String documentoPessoa;
    private String senhaPessoa;

    private String rolePessoa;

    private String endereco;

    private LocalDate dataNascimentoPessoa;

}

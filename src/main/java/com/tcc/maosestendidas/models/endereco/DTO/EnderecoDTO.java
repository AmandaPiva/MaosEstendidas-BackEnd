package com.tcc.maosestendidas.models.endereco.DTO;

import lombok.Data;

@Data
public class EnderecoDTO {
    private String logradouro;
    private String bairro;
    private String cidade;
    private String estado;
    private Integer numero;
    private String cep;
    private String pessoas;
}

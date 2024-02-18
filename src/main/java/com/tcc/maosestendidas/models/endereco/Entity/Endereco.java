package com.tcc.maosestendidas.models.endereco.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String idEndereco;

    private String logradouro;
    private String bairro;
    private String cidade;
    private Integer numero;
    private String cep;
}

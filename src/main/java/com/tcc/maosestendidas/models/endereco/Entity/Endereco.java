package com.tcc.maosestendidas.models.endereco.Entity;

import com.tcc.maosestendidas.models.pessoa.entity.Pessoa;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

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

    @OneToMany
    private Set<Pessoa> pessoas;
}

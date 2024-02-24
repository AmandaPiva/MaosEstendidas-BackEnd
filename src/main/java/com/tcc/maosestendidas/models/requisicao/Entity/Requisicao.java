package com.tcc.maosestendidas.models.requisicao.Entity;

import com.tcc.maosestendidas.models.pessoa.entity.Pessoa;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Requisicao {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String idRequisicao;

    @ManyToOne
    @JoinColumn(name = "idPessoa")
    private Pessoa pessoaDonataria;

    private String tituloRequisicao;
    private String descricaoRequisicao;

    //STATUS REQUISICAO CHAVE ESTRANGEIRA
    @Enumerated(EnumType.STRING)
    private StatusRequisicao statusRequisicao;

    //DATA EM TEMPO REAL DA REQUISIÇÃO

}

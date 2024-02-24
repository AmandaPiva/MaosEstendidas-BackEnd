package com.tcc.maosestendidas.models.requisicao.DTO;

import com.tcc.maosestendidas.models.pessoa.entity.Pessoa;
import com.tcc.maosestendidas.models.requisicao.Entity.StatusRequisicao;
import lombok.Data;

@Data
public class RequisicaoDTO {
    private String pessoaDonataria;

    private String tituloRequisicao;
    private String descricaoRequisicao;
    private StatusRequisicao statusRequisicao;

}

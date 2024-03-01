package com.tcc.maosestendidas.models.doacao.DTO;

import com.tcc.maosestendidas.models.doacao.entity.StatusDoacao;
import lombok.Data;

@Data
public class DoacaoDTO {
    private String pessoaDoadora;
    private String descricao;
    private StatusDoacao statusDoacao;
}

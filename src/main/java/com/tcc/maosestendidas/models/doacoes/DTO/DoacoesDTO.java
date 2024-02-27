package com.tcc.maosestendidas.models.doacoes.DTO;

import com.tcc.maosestendidas.models.doacoes.entity.StatusDoacao;
import lombok.Data;

@Data
public class DoacoesDTO {
    private String pessoaDoadora;
    private String descricao;
    private StatusDoacao statusDoacao;
}

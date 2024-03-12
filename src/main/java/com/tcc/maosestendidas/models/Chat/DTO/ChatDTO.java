package com.tcc.maosestendidas.models.Chat.DTO;

import com.tcc.maosestendidas.models.pessoa.entity.Pessoa;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ChatDTO {
    private String pessoaRemetente;
    private String mensagem;
    private LocalDateTime timestamp;


}

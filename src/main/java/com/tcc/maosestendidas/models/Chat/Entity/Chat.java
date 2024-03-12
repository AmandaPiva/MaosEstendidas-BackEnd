package com.tcc.maosestendidas.models.Chat.Entity;

import com.tcc.maosestendidas.models.pessoa.entity.Pessoa;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String idChat;

    @ManyToOne
    @JoinColumn(name = "idPessoa")
    private Pessoa pessoaRemetente;

    private String mensagem;
    private LocalDateTime timestamp;


}

package com.tcc.maosestendidas.models.Chat.Entity;

import com.tcc.maosestendidas.models.pessoa.entity.Pessoa;
import com.tcc.maosestendidas.models.requisicao.Entity.Requisicao;
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
    @JoinColumn(name = "idPessoaRementente")
    private Pessoa pessoaRemetente;

    @ManyToOne
    @JoinColumn(name = "idPessoaDestinataria")
    private Pessoa pessoaDestinataria;

    @ManyToOne
    @JoinColumn(name = "idRequisicao")
    private Requisicao requisicao;

    private String mensagem;
    private LocalDateTime timestamp;
}

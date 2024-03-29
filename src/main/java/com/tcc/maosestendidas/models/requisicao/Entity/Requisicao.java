package com.tcc.maosestendidas.models.requisicao.Entity;

import com.tcc.maosestendidas.models.Chat.Entity.Chat;
import com.tcc.maosestendidas.models.doacao.entity.Doacao;
import com.tcc.maosestendidas.models.pessoa.entity.Pessoa;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
public class Requisicao {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String idRequisicao;

    @ManyToOne
    @JoinColumn(name = "idPessoa")
    private Pessoa pessoaDonataria;

    @OneToMany
    private Set<Doacao> doacoes;

    @OneToMany
    private Set<Chat> mensagens;

    private String tituloRequisicao;
    private String descricaoRequisicao;

    //STATUS REQUISICAO CHAVE ESTRANGEIRA
    @Enumerated(EnumType.STRING)
    private StatusRequisicao statusRequisicao;

    //DATA EM TEMPO REAL DA REQUISIÇÃO
    private LocalDateTime dataRequisicao;
}

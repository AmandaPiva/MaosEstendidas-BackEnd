package com.tcc.maosestendidas.models.Chat.Service;

import com.tcc.maosestendidas.models.Chat.DTO.ChatDTO;
import com.tcc.maosestendidas.models.Chat.Entity.Chat;
import com.tcc.maosestendidas.models.doacao.entity.Doacao;

import java.util.List;

public interface ChatService {
    List<Chat> listarMensagens();

    Chat buscarMensagemPeloId(String idMensagem);

    List<Chat> buscarMensagemPelaPessoa(String emailPessoa);

    List<Chat> buscarMensagens(String emailPessoaRemetente, String emailPessoaDestinataria);

    //metodo de enviar mensagem
    Chat enviarMensagem(ChatDTO dto);
}

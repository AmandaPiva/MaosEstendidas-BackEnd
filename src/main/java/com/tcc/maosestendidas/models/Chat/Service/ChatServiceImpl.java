package com.tcc.maosestendidas.models.Chat.Service;

import com.tcc.maosestendidas.models.Chat.DTO.ChatDTO;
import com.tcc.maosestendidas.models.Chat.Entity.Chat;
import com.tcc.maosestendidas.models.Chat.Entity.ChatRepository;
import com.tcc.maosestendidas.models.pessoa.entity.Pessoa;
import com.tcc.maosestendidas.models.pessoa.entity.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ChatServiceImpl implements ChatService{
    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Override
    public List<Chat> listarMensagens() {
        return chatRepository.findAll();
    }

    @Override
    public Chat buscarMensagemPeloId(String idMensagem) {
        Optional<Chat> chat = chatRepository.findById(idMensagem);
        if(chat.isEmpty()) throw new RuntimeException("Erro ao buscar mensagem");

        return chat.get();
    }

    @Override
    public List<Chat> buscarMensagemPelaPessoa(String idPessoa) {
        Optional<Pessoa> pessoa = pessoaRepository.findById(idPessoa);
        if(pessoa.isEmpty()) throw new RuntimeException("Pessoa não encontrada pelo id informado");

        return chatRepository.findByPessoaRemetente(pessoa.get());
    }

    @Override
    public Chat enviarMensagem(ChatDTO dto) {
        Chat chat = converteDtoParaMensagem(dto);
        chatRepository.save(chat);

        return chat;
    }

    private Chat converteDtoParaMensagem(ChatDTO dto){
        Chat chat = new Chat();

        Optional<Pessoa> pessoa = pessoaRepository.findById(dto.getPessoaRemetente());
        if(pessoa.isEmpty()) throw new RuntimeException("Pessoa não encontrada pelo id informado");

        chat.setMensagem(dto.getMensagem());
        chat.setPessoaRemetente(pessoa.get());
        chat.setTimestamp(LocalDateTime.now());
        return chat;
    }

}

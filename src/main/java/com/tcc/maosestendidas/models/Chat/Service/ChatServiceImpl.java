package com.tcc.maosestendidas.models.Chat.Service;

import com.tcc.maosestendidas.models.Chat.DTO.ChatDTO;
import com.tcc.maosestendidas.models.Chat.Entity.Chat;
import com.tcc.maosestendidas.models.Chat.Entity.ChatRepository;
import com.tcc.maosestendidas.models.pessoa.entity.Pessoa;
import com.tcc.maosestendidas.models.pessoa.entity.PessoaRepository;
import com.tcc.maosestendidas.models.requisicao.Entity.Requisicao;
import com.tcc.maosestendidas.models.requisicao.Entity.RequisicaoRepository;
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

    @Autowired
    private RequisicaoRepository requisicaoRepository;
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
    public List<Chat> buscarMensagemPelaPessoa(String emailPessoa) {
        Optional<Pessoa> pessoa = pessoaRepository.findByEmailPessoa(emailPessoa);
        if(pessoa.isEmpty()) throw new RuntimeException("Pessoa não encontrada pelo email informado");

        return chatRepository.findByPessoaRemetente(pessoa.get());
    }

    @Override
    public List<Chat> buscarMensagens(String emailPessoaRemetente, String emailPessoaDestinataria) {
        Optional<Pessoa> pessoaRemetente = pessoaRepository.findByEmailPessoa(emailPessoaRemetente);
        if(pessoaRemetente.isEmpty()) throw new RuntimeException("Pessoa remetente não encontrada pelo email informado");

        Optional<Pessoa> pessoaDestinataria = pessoaRepository.findByEmailPessoa(emailPessoaDestinataria);
        if(pessoaDestinataria.isEmpty()) throw new RuntimeException("Pessoa destinataria não encontrada pelo email informado");


        return chatRepository.findByRemetenteAndDestinataria(pessoaRemetente.get(), pessoaDestinataria.get());
    }

    @Override
    public Chat enviarMensagem(ChatDTO dto) {
        Chat chat = converteDtoParaMensagem(dto);
        chatRepository.save(chat);

        return chat;
    }

    private Chat converteDtoParaMensagem(ChatDTO dto){
        Chat chat = new Chat();

        Optional<Pessoa> pessoaRemetente = pessoaRepository.findByEmailPessoa(dto.getPessoaRemetente());
        if(pessoaRemetente.isEmpty()) throw new RuntimeException("Pessoa remetente não encontrada pelo email informado");

        Optional<Pessoa> pessoaDestinataria = pessoaRepository.findByEmailPessoa(dto.getPessoaDestinataria());
        if(pessoaDestinataria.isEmpty()) throw new RuntimeException("Pessoa destinataria não encontrada pelo email informado");

        chat.setMensagem(dto.getMensagem());
        chat.setPessoaRemetente(pessoaRemetente.get());
        chat.setPessoaDestinataria(pessoaDestinataria.get());
        chat.setTimestamp(LocalDateTime.now());
        return chat;
    }

}

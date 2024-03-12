package com.tcc.maosestendidas.models.Chat.Entity;

import com.tcc.maosestendidas.models.pessoa.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChatRepository extends JpaRepository<Chat, String> {
    Optional<Chat> findById(String idMensagem);

    List<Chat> findByPessoaRemetente(Pessoa pessoaRemetente);
}

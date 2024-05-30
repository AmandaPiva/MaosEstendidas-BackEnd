package com.tcc.maosestendidas.models.Chat.Entity;

import com.tcc.maosestendidas.models.pessoa.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ChatRepository extends JpaRepository<Chat, String> {
    Optional<Chat> findById(String idMensagem);

    List<Chat> findByPessoaRemetente(Pessoa pessoaRementente);

    @Query("SELECT c FROM Chat c WHERE c.pessoaRemetente = :pessoaRemetente AND c.pessoaDestinataria = :pessoaDestinataria")
    List<Chat> findByRemetenteAndDestinataria(@Param("pessoaRemetente") Pessoa pessoaRemetente, @Param("pessoaDestinataria") Pessoa pessoaDestinataria);

    List<Chat> findByPessoaDestinataria(Pessoa pessoaDestinataria);

//    List<Chat> findByDestinatarioRemetente(Pessoa pessoaRementente, Pessoa pessoaDestinataria);
}

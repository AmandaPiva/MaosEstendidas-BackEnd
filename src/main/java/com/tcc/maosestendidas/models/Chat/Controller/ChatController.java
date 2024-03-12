package com.tcc.maosestendidas.models.Chat.Controller;

import com.tcc.maosestendidas.models.Chat.DTO.ChatDTO;
import com.tcc.maosestendidas.models.Chat.Entity.Chat;
import com.tcc.maosestendidas.models.Chat.Service.ChatService;
import com.tcc.maosestendidas.models.Chat.Service.ChatServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/mensagem")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @GetMapping
    public ResponseEntity<?> listarMensagens(){
        return new ResponseEntity<List<Chat>>(chatService.listarMensagens(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscaMensagemPeloId(@PathVariable("id") String id){
        return new ResponseEntity<Chat>(chatService.buscarMensagemPeloId(id), HttpStatus.OK);
    }

    @GetMapping("/buscaMensagemPeloRementente/{idPessoa}")
    public ResponseEntity<?> buscaMensagemPeloRementente(@PathVariable("idPessoa") String idPessoa){
        return new ResponseEntity<List<Chat>>(chatService.buscarMensagemPelaPessoa(idPessoa), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> mandaMensagem(@RequestBody ChatDTO dto){
        return new ResponseEntity<Chat>(chatService.enviarMensagem(dto), HttpStatus.CREATED);
    }

}

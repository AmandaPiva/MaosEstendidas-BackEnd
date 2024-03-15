package com.tcc.maosestendidas.models.doacao.Controller;

import com.tcc.maosestendidas.models.doacao.DTO.DoacaoDTO;
import com.tcc.maosestendidas.models.doacao.Service.DoacaoService;
import com.tcc.maosestendidas.models.doacao.entity.Doacao;
import com.tcc.maosestendidas.models.doacao.entity.StatusDoacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/doacoes")
public class DoacaoController {
    @Autowired
    private DoacaoService doacaoService;

    @GetMapping
    public ResponseEntity<?> listaDoacoes(){
        return new ResponseEntity<List<Doacao>>(doacaoService.listarDoacoes(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscaDoacaoPeloId(@PathVariable("id") String id){
        return new ResponseEntity<Doacao>(doacaoService.buscarDoacaoPeloId(id), HttpStatus.OK);
    }

    @GetMapping("/buscaDoacoesPelaPessoa/{idPessoa}")
    public ResponseEntity<?> buscaDoacaoPelaPessoa(@PathVariable("idPessoa") String idPessoa){
        return new ResponseEntity<List<Doacao>>(doacaoService.buscarDoacaoPelaPessoa(idPessoa), HttpStatus.OK);
    }

    @GetMapping("/buscaDoacaoPeloStatus/{statusDoacao}")
    public List<Doacao> buscarDoacaoPeloStatus(@PathVariable String statusDoacao){
        StatusDoacao status = StatusDoacao.valueOf(statusDoacao.toUpperCase());
        return doacaoService.buscarDoacaoPeloStatus(status);
    }

    @PatchMapping("/mudarStatusDoacao/{id}/{statusDoacao}")
    public ResponseEntity<?> atualizarStatusDoacao(@PathVariable("id") String id, @PathVariable String statusDoacao){
        try {
            StatusDoacao novoStatus = StatusDoacao.valueOf(statusDoacao.toUpperCase());
            Doacao doacaoAtualizada = doacaoService.updateStatusDoacao(id, novoStatus);
            return new ResponseEntity<>(doacaoAtualizada, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Status de doação inválido", HttpStatus.BAD_REQUEST);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> criaDoacao(@RequestBody DoacaoDTO dto){
        return new ResponseEntity<Doacao>(doacaoService.criaDoacao(dto), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateDoacao(@RequestBody DoacaoDTO dto, @PathVariable("id") String id){
        return new ResponseEntity<Doacao>(doacaoService.updateDoacao(dto, id), HttpStatus.OK);
    }
}

package com.tcc.maosestendidas.models.pessoa.controller;

import com.tcc.maosestendidas.models.pessoa.DTO.PessoaDTO;
import com.tcc.maosestendidas.models.pessoa.entity.Pessoa;
import com.tcc.maosestendidas.models.pessoa.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pessoa")
public class PessoaController {
    @Autowired
    private PessoaService pessoaService;



    @GetMapping
    public ResponseEntity<?> listaPessoas(){
        return new ResponseEntity<List<Pessoa>>(pessoaService.listaPessoas(), HttpStatus.OK);
    }

    @GetMapping("/buscaPeloEmail/{emailPessoa}")
    public ResponseEntity<?> buscarPessoaPeloEmail(@PathVariable("emailPessoa") String emailPessoa){
        return new ResponseEntity<Pessoa>(pessoaService.buscaPessoaPeloEmail(emailPessoa), HttpStatus.OK);
    }

    @GetMapping("/buscarPeloCpf/{cpfPessoa}")
    public ResponseEntity<?> buscarPessoaPeloCpf(@PathVariable("cpfPessoa") String cpfPessoa){
        return new ResponseEntity<Pessoa>(pessoaService.buscaPessoaPeloCpf(cpfPessoa), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> criaPessoa(@RequestBody PessoaDTO dto){
        return new ResponseEntity<Pessoa>(pessoaService.criaPessoa(dto), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updatePessoa(@RequestBody PessoaDTO dto, @PathVariable("id") String id){
        return new ResponseEntity<Pessoa>(pessoaService.updatePessoa(dto,id), HttpStatus.OK);
    }


}

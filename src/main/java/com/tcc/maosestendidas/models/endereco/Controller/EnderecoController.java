package com.tcc.maosestendidas.models.endereco.Controller;

import com.tcc.maosestendidas.models.endereco.DTO.EnderecoDTO;
import com.tcc.maosestendidas.models.endereco.Entity.Endereco;
import com.tcc.maosestendidas.models.endereco.Service.EnderecoService;
import com.tcc.maosestendidas.models.pessoa.DTO.PessoaDTO;
import com.tcc.maosestendidas.models.pessoa.entity.Pessoa;
import jdk.jshell.PersistentSnippet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/endereco")
public class EnderecoController {
    @Autowired
    private EnderecoService enderecoService;


    @GetMapping
    public ResponseEntity<?> listaEndereco(){
        return new ResponseEntity<List<Endereco>>(enderecoService.listaEnderecos(), HttpStatus.OK);
    }

    @GetMapping("/buscaPeloLogradouro/{logradouro}")
    public ResponseEntity<?> buscaEnderecoPeloLogradouro(@PathVariable("logradouro") String logradouro){
        return new ResponseEntity<Endereco>(enderecoService.buscaEnderecoPeloLogradouro(logradouro), HttpStatus.OK);
    }

    @GetMapping("/buscaPeloCep/{cep}")
    public ResponseEntity<?> buscaEnderecoPeloCep(@PathVariable("cep") String cep){
        return new ResponseEntity<Endereco>(enderecoService.buscaEnderecoPeloCep(cep), HttpStatus.OK);
    }

    @GetMapping("/buscaPeloBairro/{bairro}")
    public ResponseEntity<?> buscaEnderecoPeloBairro(@PathVariable("bairro") String bairro){
        return new ResponseEntity<Endereco>(enderecoService.listarEnderecosPeloBairro(bairro), HttpStatus.OK);
    }

    @GetMapping("/buscaPelaCidade/{cidade}")
    public ResponseEntity<?> buscarEnderecoPelaCidade(@PathVariable("cidade") String cidade){
        return new ResponseEntity<Endereco>(enderecoService.listarEnderecosPelaCidade(cidade), HttpStatus.OK);
    }

    @GetMapping("/buscaPeloEstado/{estado}")
    public ResponseEntity<?> buscarEnderecoPeloEstado(@PathVariable("estado") String estado){
        return new ResponseEntity<Endereco>(enderecoService.listaEnderecosPeloEstado(estado), HttpStatus.OK);
    }

    @GetMapping("/viacep/{cep}")
    public ResponseEntity<Endereco> listaEndereco(@PathVariable("cep") String cep) {
        return new ResponseEntity<Endereco>(enderecoService.listaEnderecoViaCep(cep), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> criaEndereco(@RequestBody EnderecoDTO dto){
        return new ResponseEntity<Endereco>(enderecoService.criaEndereco(dto), HttpStatus.CREATED);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<?> updateEndereco(@RequestBody EnderecoDTO dto, @PathVariable("id") String id) {
        return new ResponseEntity<Endereco>(enderecoService.updateEndereco(dto, id), HttpStatus.OK);
    }

}

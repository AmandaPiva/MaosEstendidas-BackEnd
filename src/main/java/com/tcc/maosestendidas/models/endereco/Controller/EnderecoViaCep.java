package com.tcc.maosestendidas.models.endereco.Controller;

import com.tcc.maosestendidas.models.endereco.Entity.Endereco;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/endereco")
public class EnderecoViaCep {

    @GetMapping("/viacep.com.br/ws/{cep}/json/")
    public ResponseEntity<?> buscarEnderecoPeloCep(@PathVariable("cep") String cep){
        return new ResponseEntity<>(cep, HttpStatus.OK);
    }
}

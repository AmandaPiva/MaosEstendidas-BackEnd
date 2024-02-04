package com.tcc.maosestendidas.models.pessoa.controller;

import com.tcc.maosestendidas.models.pessoa.DTO.PessoaLoginDTO;
import com.tcc.maosestendidas.models.pessoa.service.PessoaRoleService;
import com.tcc.maosestendidas.models.pessoa.service.PessoaService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.logging.Logger;

public class PessoaLoginController {
    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private PessoaRoleService pessoaRoleService;

    private final Logger log = (Logger) LoggerFactory.getLogger(PessoaLoginController.class);

    @GetMapping("/valida")
    public ResponseEntity<String> validaToken(){
        return new ResponseEntity<String>("OK", HttpStatus.OK);
    }

//    @PostMapping("/executa")
//    public ResponseEntity<?> executa(@RequestBody PessoaLoginDTO dto){
//        try{
//            log.info("Pedido de login recebido: " + dto.toString());
//            return ResponseEntity.ok()
//        }
//    }


}

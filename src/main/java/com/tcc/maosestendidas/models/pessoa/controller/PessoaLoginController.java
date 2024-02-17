package com.tcc.maosestendidas.models.pessoa.controller;

import com.tcc.maosestendidas.Autenticacao.Service.AutenticacaoService;
import com.tcc.maosestendidas.models.pessoa.DTO.PessoaLoginDTO;
import com.tcc.maosestendidas.models.pessoa.DTO.PessoaRoleDTO;
import com.tcc.maosestendidas.models.pessoa.entity.Pessoa;
import com.tcc.maosestendidas.models.pessoa.entity.PessoaRole;
import com.tcc.maosestendidas.models.pessoa.service.PessoaRoleService;
import com.tcc.maosestendidas.models.pessoa.service.PessoaService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/api/v1/login")

public class PessoaLoginController {
    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private PessoaRoleService pessoaRoleService;

    @Autowired
    private AutenticacaoService autenticacaoService;

    private final Logger log = LoggerFactory.getLogger(PessoaLoginController.class);

    @GetMapping("/valida")
    public ResponseEntity<String> validaToken(){
        return new ResponseEntity<String>("OK", HttpStatus.OK);
    }

    @PostMapping("/executa")
    public ResponseEntity<?> executa(@RequestBody PessoaLoginDTO dto){
        try{
            log.info("Pedido de login recebido: " + dto.toString());
            return ResponseEntity.ok(autenticacaoService.assina(dto));
        }catch (Error ex){
            return new ResponseEntity<String>(ex.getMessage(), null, HttpStatus.BAD_REQUEST);
        }
    }

    @Bean
    private void criaLoginAdmin(){
        Optional<Pessoa> pessoaVerifica = pessoaService.buscaPessoaPeloEmailOptional("maosestendidas@teste.com.br");
        if(pessoaVerifica.isEmpty()) {
            log.warn("Usuário admin não existe e será criado");
            Optional<PessoaRole> roleVerifica = pessoaRoleService.buscarPessoaRolePeloNomeOptional("ADMIN");
            Pessoa pessoa = new Pessoa();

            if(roleVerifica.isEmpty()){
                PessoaRole role = criaRoleAdmin();
                pessoa.setRolePessoa(role);
            }else{
                pessoa.setRolePessoa(roleVerifica.get());
            }
            pessoa.setNomePessoa("MaosEstendidas");
            pessoa.setEmailPessoa("maosestendidas@teste.com.br");
            pessoa.setSenhaPessoa(geraSenhaHash());
            pessoaService.criaPessoa(pessoa);

        }else{
            log.info("Usuário admin já existe!");
        }
    }

    private PessoaRole criaRoleAdmin() {
        Optional<PessoaRole> roleVerifica = pessoaRoleService.buscarPessoaRolePeloNomeOptional("ADMIN");
        if(roleVerifica.isEmpty()) {
            PessoaRoleDTO role = new PessoaRoleDTO();
            role.setRolePessoa("ADMIN");
            return pessoaRoleService.criaPessoaRole(role);
        }
        else {
            return roleVerifica.get();
        }
    }

    private String geraSenhaHash() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return  passwordEncoder.encode("T1burc10@2022");
    }

}

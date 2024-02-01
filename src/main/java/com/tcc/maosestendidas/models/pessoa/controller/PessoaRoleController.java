package com.tcc.maosestendidas.models.pessoa.controller;

import com.tcc.maosestendidas.models.pessoa.DTO.PessoaRoleDTO;
import com.tcc.maosestendidas.models.pessoa.entity.PessoaRole;
import com.tcc.maosestendidas.models.pessoa.service.PessoaRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pessoa/role")
public class PessoaRoleController {
    @Autowired
    private PessoaRoleService pessoaRoleService;

    @GetMapping
    public ResponseEntity<?> listaPessoaRoles(){
        return new ResponseEntity<List<PessoaRole>>(pessoaRoleService.listaPessoaRoles(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> criaPessoaRole(@RequestBody PessoaRoleDTO dto){
        return new ResponseEntity<PessoaRole>(pessoaRoleService.criaPessoaRole(dto), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updatePessoaRole(@RequestBody PessoaRoleDTO dto, @PathVariable("id") String id){
        return new ResponseEntity<PessoaRole>(pessoaRoleService.updatePessoaRole(dto, id), HttpStatus.OK);
    }
}

package com.tcc.maosestendidas.models.doacoes.Controller;

import com.tcc.maosestendidas.models.doacoes.DTO.DoacoesDTO;
import com.tcc.maosestendidas.models.doacoes.Service.DoacoesService;
import com.tcc.maosestendidas.models.doacoes.entity.Doacoes;
import com.tcc.maosestendidas.models.doacoes.entity.StatusDoacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/doacoes")
public class DoacoesController {
    @Autowired
    private DoacoesService doacoesService;

    @GetMapping
    public ResponseEntity<?> listaDoacoes(){
        return new ResponseEntity<List<Doacoes>>(doacoesService.listarDoacoes(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscaDoacaoPeloId(@PathVariable("id") String id){
        return new ResponseEntity<Doacoes>(doacoesService.buscarDoacaoPeloId(id), HttpStatus.OK);
    }

    @GetMapping("/buscaDoacoesPelaPessoa/{idPessoa}")
    public ResponseEntity<?> buscaDoacaoPelaPessoa(@PathVariable("idPessoa") String idPessoa){
        return new ResponseEntity<List<Doacoes>>(doacoesService.buscarDoacaoPelaPessoa(idPessoa), HttpStatus.OK);
    }

    @GetMapping("/buscaDoacaoPeloStatus/{statusDoacao}")
    public List<Doacoes> buscarDoacaoPeloStatus(@PathVariable String statusDoacao){
        StatusDoacao status = StatusDoacao.valueOf(statusDoacao.toUpperCase());
        return doacoesService.buscarDoacaoPeloStatus(status);
    }

    @PostMapping
    public ResponseEntity<?> criaDoacao(@RequestBody DoacoesDTO dto){
        return new ResponseEntity<Doacoes>(doacoesService.criaDoacao(dto), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateDoacao(@RequestBody DoacoesDTO dto, @PathVariable("id") String id){
        return new ResponseEntity<Doacoes>(doacoesService.updateDoacao(dto, id), HttpStatus.OK);
    }
}

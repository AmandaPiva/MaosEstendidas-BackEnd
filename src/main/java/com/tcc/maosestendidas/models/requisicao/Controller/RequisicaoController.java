package com.tcc.maosestendidas.models.requisicao.Controller;

import com.tcc.maosestendidas.models.requisicao.DTO.RequisicaoDTO;
import com.tcc.maosestendidas.models.requisicao.Entity.Requisicao;
import com.tcc.maosestendidas.models.requisicao.Entity.StatusRequisicao;
import com.tcc.maosestendidas.models.requisicao.Service.RequisicaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/requisicao")
public class RequisicaoController {
    @Autowired
    private RequisicaoService requisicaoService;

    @GetMapping
    public ResponseEntity<?> listaRequisicoes(){
        return new ResponseEntity<List<Requisicao>>(requisicaoService.listarRequisicoes(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscaRequisicaoPeloId(@PathVariable("id") String id){
        return new ResponseEntity<Requisicao>(requisicaoService.buscarRequisicaoPeloId(id), HttpStatus.OK);
    }

    @GetMapping("/buscaRequisicoesPelaPessoa/{idPessoa}")
    public ResponseEntity<?> buscaRequisicaoPelaPessoa(@PathVariable("idPessoa") String idPessoa){
        return new ResponseEntity<List<Requisicao>>(requisicaoService.buscarRequisicaoPelaPessoa(idPessoa), HttpStatus.OK);
    }

    @GetMapping("/buscaRequsicoesPeloStatus/{statusRequisicao}")
    public List<Requisicao> buscarRequisicoesPeloStatus(@PathVariable String statusRequisicao) {
        StatusRequisicao status = StatusRequisicao.valueOf(statusRequisicao.toUpperCase());
        return requisicaoService.buscarRequisicaoPeloStatus(status);
    }


    @PostMapping
    public ResponseEntity<?> criaRequisicao(@RequestBody RequisicaoDTO dto){
        return new ResponseEntity<Requisicao>(requisicaoService.criaRequisicao(dto), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateRequisicao(@RequestBody RequisicaoDTO dto, @PathVariable("id") String id){
        return new ResponseEntity<Requisicao>(requisicaoService.updateRequisicao(dto, id), HttpStatus.OK);
    }

    //DISCUTIR A POSSIBILIDADE DE TER UPDATE DO STATUS DA REQUISICAO
    

    @DeleteMapping("/{id}")
    public void deleteRequisicao(@PathVariable("id") String id){
        requisicaoService.deleteRequisicao(id);
    }

}

package com.tcc.maosestendidas.models.pessoa.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tcc.maosestendidas.models.pessoa.DTO.PessoaDTO;
import com.tcc.maosestendidas.models.pessoa.entity.Pessoa;
import com.tcc.maosestendidas.models.pessoa.service.PessoaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static java.rmi.server.LogStream.log;

@RestController
@RequestMapping("/api/v1/pessoa")
@Slf4j
public class PessoaController {
    @Autowired
    private PessoaService pessoaService;

    @GetMapping
    public ResponseEntity<?> listaPessoas(){
        return new ResponseEntity<List<Pessoa>>(pessoaService.listaPessoas(), HttpStatus.OK);
    }

    @GetMapping("/listaPessoasPelaRole/{rolePessoa}")
    public ResponseEntity<?> listarPessoasPelaRole(@PathVariable("rolePessoa") String rolePessoa){
        return new ResponseEntity<List<Pessoa>>(pessoaService.listarPessoasPelaRole(rolePessoa), HttpStatus.OK);
    }


    @GetMapping("/buscaPeloEmail/{emailPessoa}")
    public ResponseEntity<?> buscarPessoaPeloEmail(@PathVariable("emailPessoa") String emailPessoa){
        return new ResponseEntity<Pessoa>(pessoaService.buscaPessoaPeloEmail(emailPessoa), HttpStatus.OK);
    }

    @GetMapping("/buscarPeloDocumento/{documentoPessoa}")
    public ResponseEntity<?> buscarPessoaPeloDocumento(@PathVariable("documentoPessoa") String documentoPessoa){
        return new ResponseEntity<Pessoa>(pessoaService.buscaPessoaPeloDocumento(documentoPessoa), HttpStatus.OK);
    }

    @GetMapping("/buscarPeloCelular/{celularPessoa}")
    public ResponseEntity<?> buscarPessoaPeloCelular(@PathVariable("celularPessoa") String celularPessoa){
        return new ResponseEntity<Pessoa>(pessoaService.buscaPessoaPeloCelular(celularPessoa), HttpStatus.OK);
    }

//    @PostMapping("/{id}/upload-imagem")
//    public ResponseEntity<String> uploadImagem(@PathVariable("id") String idPessoa,
//                                               @RequestParam("imagem") MultipartFile imagem) {
//        try {
//            Pessoa pessoa = pessoaService.uploadImagem(idPessoa, imagem);
//            return ResponseEntity.ok("Imagem carregada com sucesso para a pessoa: " + pessoa.getIdPessoa());
//        } catch (RuntimeException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//        } catch (IOException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao processar a imagem: " + e.getMessage());
//        }
//    }

    @GetMapping("/imagem/{idPessoa}")
    public ResponseEntity<byte[]> getImagem(@PathVariable String idPessoa) {
        byte[] imagem = pessoaService.getImagemByIdPessoa(idPessoa);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG); // ou o tipo de mídia apropriado para sua imagem
        return new ResponseEntity<>(imagem, headers, HttpStatus.OK);
    }

//    @PostMapping
//    public ResponseEntity<?> criaPessoa(@RequestParam("pessoaDto") String pessoa, @RequestParam("imagem") MultipartFile imagem) throws IOException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        PessoaDTO pessoaDto = objectMapper.readValue(pessoa, PessoaDTO.class);
//        return new ResponseEntity<Pessoa>(pessoaService.criaPessoa(pessoaDto, imagem), HttpStatus.CREATED);
//    }

    @PostMapping
    public ResponseEntity<?> criaPessoa(@RequestBody PessoaDTO dto){
        return new ResponseEntity<Pessoa>(pessoaService.criaPessoa(dto), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updatePessoa(@RequestBody PessoaDTO dto, @PathVariable("id") String id){
        return new ResponseEntity<Pessoa>(pessoaService.updatePessoa(dto,id), HttpStatus.OK);
    }


//    @PatchMapping("/updateSenha/{email}")
//    public ResponseEntity<?> updateSenhaPessoa(@RequestBody S, @PathVariable("email") String email){
//        return new ResponseEntity<Pessoa>(pessoaService.updateSenha(dto, email), HttpStatus.OK);
//    }




}

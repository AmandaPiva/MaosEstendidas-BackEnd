package com.tcc.maosestendidas.Autenticacao.Service;

import com.tcc.maosestendidas.JWT.DTO.JwtAuthResponseDTO;
import com.tcc.maosestendidas.models.pessoa.DTO.PessoaLoginDTO;

public interface AutenticacaoService {
    JwtAuthResponseDTO assina (PessoaLoginDTO dto);//Metodo que aceita O DTO pessoa com as informações dela
    //este método retorna informações sobre a autenticação
}

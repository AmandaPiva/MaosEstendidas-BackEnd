package com.tcc.maosestendidas.models.endereco.Service;

import com.tcc.maosestendidas.models.endereco.DTO.EnderecoDTO;
import com.tcc.maosestendidas.models.endereco.Entity.Endereco;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public interface EnderecoService {
    List<Endereco> listaEnderecos();

    Endereco buscaEnderecoPeloLogradouro(String logradouro);

    Endereco buscaEnderecoPeloCep(String cep);

    Endereco listarEnderecosPeloBairro(String bairro);

    Endereco listarEnderecosPelaCidade(String cidade);



    Endereco cadastrarEnderecoManual(EnderecoDTO dto);

    Endereco updateEndereco(EnderecoDTO dto, String idEndereco);


}

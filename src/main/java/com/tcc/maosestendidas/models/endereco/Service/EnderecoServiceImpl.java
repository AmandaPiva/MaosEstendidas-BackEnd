package com.tcc.maosestendidas.models.endereco.Service;

import com.tcc.maosestendidas.models.endereco.DTO.EnderecoDTO;
import com.tcc.maosestendidas.models.endereco.Entity.Endereco;
import com.tcc.maosestendidas.models.endereco.Entity.EnderecoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoServiceImpl implements EnderecoService{

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Override
    public List<Endereco> listaEnderecos() {
        return enderecoRepository.findAll();
    }

    @Override
    public Endereco buscaEnderecoPeloLogradouro(String logradouro) {
        Optional<Endereco> endereco = enderecoRepository.findByLogradouro(logradouro);
        if(endereco.isEmpty()) throw new RuntimeException("Endereço não encontrado pelo logradouro informado");

        return endereco.get();
    }

    @Override
    public Endereco buscaEnderecoPeloCep(String cep) {
        Optional<Endereco> endereco = enderecoRepository.findByCep(cep);
        if(endereco.isEmpty()) throw new RuntimeException("Endereço não encontrado pelo cep informado");

        return endereco.get();
    }

    @Override
    public Endereco listarEnderecosPeloBairro(String bairro) {
        Optional<Endereco> endereco = enderecoRepository.findByBairro(bairro);
        if(endereco.isEmpty()) throw new RuntimeException("Endereço não encontrado pelo bairro informado");


        return endereco.get();
    }

    @Override
    public Endereco listarEnderecosPelaCidade(String cidade) {
        Optional<Endereco> endereco = enderecoRepository.findByCidade(cidade);
        if(endereco.isEmpty()) throw new RuntimeException("Endereço não encontrado pelo cidade informado");

        return endereco.get();
    }

    /*@Override
    @Transactional
    public Endereco cadastrarEnderecoPeloCep() {
        return null;
    }*/

    @Override
    @Transactional
    public Endereco cadastrarEnderecoManual(EnderecoDTO dto) {
        Endereco endereco = converteDtoParaEndereco(dto);
        enderecoRepository.save(endereco);

        return endereco;
    }

    private Endereco converteDtoParaEndereco(EnderecoDTO dto){
        Endereco endereco = new Endereco();

        endereco.setLogradouro(dto.getLogradouro());
        endereco.setBairro(dto.getBairro());
        endereco.setCidade(dto.getCidade());
        endereco.setCep(dto.getCep());
        endereco.setNumero(dto.getNumero());

        return endereco;
    }

    @Override
    public Endereco updateEndereco(EnderecoDTO dto, String idEndereco) {
        Optional<Endereco> endereco = enderecoRepository.findById(idEndereco);
        if(endereco.isEmpty()) throw new RuntimeException("Endereço não encontrado pelo id informado");

        Endereco updateEndereco = endereco.get();

        updateEndereco.setLogradouro(dto.getLogradouro());
        updateEndereco.setBairro(dto.getBairro());
        updateEndereco.setCidade(dto.getCidade());
        updateEndereco.setCep(dto.getCep());
        updateEndereco.setNumero(dto.getNumero());


        return updateEndereco;
    }
}

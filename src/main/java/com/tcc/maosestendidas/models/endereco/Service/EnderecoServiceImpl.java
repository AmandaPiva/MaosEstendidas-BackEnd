package com.tcc.maosestendidas.models.endereco.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tcc.maosestendidas.Config.JwtAuthenticationFilter;
import com.tcc.maosestendidas.models.endereco.DTO.EnderecoDTO;
import com.tcc.maosestendidas.models.endereco.DTO.EnderecoViaCepDTO;
import com.tcc.maosestendidas.models.endereco.Entity.Endereco;
import com.tcc.maosestendidas.models.endereco.Entity.EnderecoRepository;
import com.tcc.maosestendidas.models.pessoa.entity.Pessoa;
import com.tcc.maosestendidas.models.pessoa.entity.PessoaRepository;
import jakarta.transaction.Transactional;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

@Service
public class EnderecoServiceImpl implements EnderecoService{

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;


    public List<Endereco> listaEnderecos() {
        return enderecoRepository.findAll();
    }



    public Endereco buscaEnderecoPeloCep(String cep) {
        Optional<Endereco> endereco = enderecoRepository.findByCep(cep);
        if(endereco.isEmpty()) throw new RuntimeException("Endereço não encontrado pelo cep informado");

        return endereco.get();
    }

    @Override
    public Endereco buscaEnderecoPeloLogradouro(String logradouro) {
        Optional<Endereco> endereco = enderecoRepository.findByLogradouro(logradouro);
        if(endereco.isEmpty()) throw new RuntimeException("Endereço não encontrado pelo logradouro informado");

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

    //busca endereco pelo cep usando a API viacep
    @Override
    public Endereco listaEnderecoViaCep(String cep) {
        if(cep.isEmpty()) throw new RuntimeException("Cep não informado");
        Optional<Endereco> endereco = enderecoRepository.findByCep(cep);
        if(endereco.isEmpty()){
            System.out.println("Endereço não encontrado no banco de dados, buscando no viacep");
            return buscaEnderecoViaCep(cep);
        }
        else{
            return endereco.get();
        }
    }

    @Override
    public Endereco listaEnderecosPeloEstado(String estado) {
        Optional<Endereco> endereco = enderecoRepository.findByEstado(estado);
        if(endereco.isEmpty()) throw new RuntimeException("Endereço não encontrado pelo estado informado");

        return endereco.get();
    }

    //existem dois tipos do método criaEndereco, um se aplica ao endereco sendo inserido manualmente, o outro quando for buscar o endereço pela API viacep
    @Transactional
    public Endereco criaEndereco(Endereco endereco) {
        enderecoRepository.save(endereco);
        return endereco;
    }

    @Override
    @Transactional
    public Endereco criaEndereco(EnderecoDTO dto){
        Endereco endereco = converteDtoParaEndereco(dto);
        enderecoRepository.save(endereco);
        return endereco;
    }

    private final Logger log = LoggerFactory.getLogger(EnderecoServiceImpl.class);

    private Endereco buscaEnderecoViaCep(String cep) {
        try {

            log.info("Buscando CEP no via cep: " + cep);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://viacep.com.br/ws/"+cep+"/json/"))
                    .GET()
                    .build();

            HttpClient client = HttpClient.newHttpClient();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String json = response.body();

            EnderecoViaCepDTO e = new ObjectMapper().readValue(json, EnderecoViaCepDTO.class);
            e.setCep(cep);
            return criaEndereco(converteEnderecoViaCep(e)); //Cria endereco com os dados vindos do DTO da API viacep

        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new RuntimeException("Endereco não encontrado pelo cep informado.");
        }
    }

    private Endereco converteEnderecoViaCep(EnderecoViaCepDTO dto) {
        Endereco endereco = new Endereco();
        endereco.setCep(dto.getCep());
        endereco.setCidade(dto.getLocalidade());
        endereco.setEstado(dto.getUf());
        endereco.setLogradouro(dto.getLogradouro());
        endereco.setBairro(dto.getBairro());

        return endereco;
    }

    private Endereco converteDtoParaEndereco(EnderecoDTO dto){
        Endereco endereco = new Endereco();

        endereco.setLogradouro(dto.getLogradouro());
        endereco.setBairro(dto.getBairro());
        endereco.setCidade(dto.getCidade());
        endereco.setEstado(dto.getEstado());
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
        updateEndereco.setEstado(dto.getEstado());
        updateEndereco.setCidade(dto.getCidade());
        updateEndereco.setCep(dto.getCep());
        updateEndereco.setNumero(dto.getNumero());

        enderecoRepository.save(updateEndereco);

        return updateEndereco;
    }
}

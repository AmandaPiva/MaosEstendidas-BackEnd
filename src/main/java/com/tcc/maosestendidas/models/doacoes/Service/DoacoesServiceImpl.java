package com.tcc.maosestendidas.models.doacoes.Service;

import com.tcc.maosestendidas.models.doacoes.DTO.DoacoesDTO;
import com.tcc.maosestendidas.models.doacoes.entity.Doacoes;
import com.tcc.maosestendidas.models.doacoes.entity.DoacoesRepository;
import com.tcc.maosestendidas.models.pessoa.entity.Pessoa;
import com.tcc.maosestendidas.models.pessoa.entity.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoacoesServiceImpl implements DoacoesService{
    @Autowired
    private DoacoesRepository doacoesRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Override
    public List<Doacoes> listarDoacoes() {
        return doacoesRepository.findAll();
    }

    @Override
    public Doacoes buscarDoacaoPeloId(String idDoacao) {
        Optional<Doacoes> doacoes = doacoesRepository.findById(idDoacao);
        if(doacoes.isEmpty()) throw new RuntimeException("Doação não encontrada pelo id informado");


        return doacoes.get();
    }

    @Override
    public List<Doacoes> buscarDoacaoPelaPessoa(String idPessoa) {
        Optional<Pessoa> pessoa = pessoaRepository.findById(idPessoa);
        if(pessoa.isEmpty()) throw new RuntimeException("Pessoa não encontrada pelo id informado");

        return doacoesRepository.findByPessoaDoadora(pessoa.get());
    }

    @Override
    public Doacoes criaDoacao(DoacoesDTO dto) {
        Doacoes doacoes = converteDtoParaDoacao(dto);
        doacoesRepository.save(doacoes);

        return doacoes;
    }

    private Doacoes converteDtoParaDoacao(DoacoesDTO dto){
        Doacoes doacoes = new Doacoes();

        Optional<Pessoa> pessoa = pessoaRepository.findById(dto.getPessoaDoadora());
        if(pessoa.isEmpty()) throw new RuntimeException("Pessoa não encontrada pelo id informado");

        doacoes.setDescricao(dto.getDescricao());
        doacoes.setPessoaDoadora(pessoa.get());

        return doacoes;

    }

    @Override
    public Doacoes updateDoacao(DoacoesDTO dto, String idDoacao) {
        Optional<Doacoes> doacoes = doacoesRepository.findById(idDoacao);
        if(doacoes.isEmpty()) throw new RuntimeException("Doação não encontrada pelo id informado");

        Doacoes updateDoacao = doacoes.get();

        updateDoacao.setDescricao(dto.getDescricao());

        return updateDoacao;
    }
}

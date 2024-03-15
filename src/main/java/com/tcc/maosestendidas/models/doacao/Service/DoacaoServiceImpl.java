package com.tcc.maosestendidas.models.doacao.Service;

import com.tcc.maosestendidas.models.doacao.DTO.DoacaoDTO;
import com.tcc.maosestendidas.models.doacao.entity.Doacao;
import com.tcc.maosestendidas.models.doacao.entity.DoacaoRepository;
import com.tcc.maosestendidas.models.doacao.entity.StatusDoacao;
import com.tcc.maosestendidas.models.pessoa.entity.Pessoa;
import com.tcc.maosestendidas.models.pessoa.entity.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DoacaoServiceImpl implements DoacaoService {
    @Autowired
    private DoacaoRepository doacaoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Override
    public List<Doacao> listarDoacoes() {
        return doacaoRepository.findAll();
    }

    @Override
    public Doacao buscarDoacaoPeloId(String idDoacao) {
        Optional<Doacao> doacao = doacaoRepository.findById(idDoacao);
        if(doacao.isEmpty()) throw new RuntimeException("Doação não encontrada pelo id informado");


        return doacao.get();
    }

    @Override
    public List<Doacao> buscarDoacaoPelaPessoa(String idPessoa) {
        Optional<Pessoa> pessoa = pessoaRepository.findById(idPessoa);
        if(pessoa.isEmpty()) throw new RuntimeException("Pessoa não encontrada pelo id informado");

        return doacaoRepository.findByPessoaDoadora(pessoa.get());
    }

    @Override
    public List<Doacao> buscarDoacaoPeloStatus(StatusDoacao statusDoacao) {
        return doacaoRepository.findAll().stream()
                .filter(doacoes -> doacoes.getStatusDoacao() == statusDoacao)
                .collect(Collectors.toList());
    }

    @Override
    public Doacao criaDoacao(DoacaoDTO dto) {
        Doacao doacao = converteDtoParaDoacao(dto);
        doacaoRepository.save(doacao);

        return doacao;
    }

    private Doacao converteDtoParaDoacao(DoacaoDTO dto){
        Doacao doacao = new Doacao();

        Optional<Pessoa> pessoa = pessoaRepository.findById(dto.getPessoaDoadora());
        if(pessoa.isEmpty()) throw new RuntimeException("Pessoa não encontrada pelo id informado");

        doacao.setDescricao(dto.getDescricao());
        doacao.setPessoaDoadora(pessoa.get());
        doacao.setStatusDoacao(dto.getStatusDoacao());
        doacao.setDataDoacao(LocalDateTime.now());

        return doacao;

    }

    @Override
    public Doacao updateStatusDoacao(String idDoacao, StatusDoacao novoStatus) {
        Optional<Doacao> doacaoOptional = doacaoRepository.findById(idDoacao);
        if (doacaoOptional.isEmpty()) {
            throw new RuntimeException("Doação não encontrada pelo id informado");
        }

        Doacao doacao = doacaoOptional.get();
        doacao.setStatusDoacao(novoStatus);
        doacao.setDataDoacao(LocalDateTime.now());

        return doacaoRepository.save(doacao);
    }

    @Override
    public Doacao updateDoacao(DoacaoDTO dto, String idDoacao) {
        Optional<Doacao> doacao = doacaoRepository.findById(idDoacao);
        if(doacao.isEmpty()) throw new RuntimeException("Doação não encontrada pelo id informado");

        Doacao updateDoacao = doacao.get();

        updateDoacao.setDescricao(dto.getDescricao());
        updateDoacao.setStatusDoacao(dto.getStatusDoacao());

        return updateDoacao;
    }
}

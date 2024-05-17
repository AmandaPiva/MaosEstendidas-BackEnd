package com.tcc.maosestendidas.models.doacao.Service;

import com.tcc.maosestendidas.models.doacao.DTO.DoacaoDTO;
import com.tcc.maosestendidas.models.doacao.DTO.VinculaDoacaoNaRequisicaoDTO;
import com.tcc.maosestendidas.models.doacao.entity.Doacao;
import com.tcc.maosestendidas.models.doacao.entity.DoacaoRepository;
import com.tcc.maosestendidas.models.doacao.entity.StatusDoacao;
import com.tcc.maosestendidas.models.pessoa.entity.Pessoa;
import com.tcc.maosestendidas.models.pessoa.entity.PessoaRepository;
import com.tcc.maosestendidas.models.requisicao.Entity.Requisicao;
import com.tcc.maosestendidas.models.requisicao.Entity.RequisicaoRepository;
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

    @Autowired
    private RequisicaoRepository requisicaoRepository;

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

//    @Override
//    public List<Doacao> buscarDoacaoPeloStatus(StatusDoacao statusDoacao) {
//        return doacaoRepository.findAll().stream()
//                .filter(doacoes -> doacoes.getStatusDoacao() == statusDoacao)
//                .collect(Collectors.toList());
//    }

    @Override
    public Requisicao vinculaDoacaoNaRequisicao(VinculaDoacaoNaRequisicaoDTO dto){
        Optional<Doacao> doacao = doacaoRepository.findById(dto.getIdDoacao());
        if(doacao.isEmpty()) throw new RuntimeException("Doação ainda não criada");

        Optional<Requisicao> requisicao = requisicaoRepository.findById(dto.getIdRequisicao());
        if(requisicao.isEmpty()) throw new RuntimeException("Requisição não encontrada pelo id informado");

        Requisicao requisicao1 = requisicao.get();

        requisicao1.getDoacoes().add(doacao.get());

        requisicaoRepository.save(requisicao1);

        return requisicao1;
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

        doacao.setPessoaDoadora(pessoa.get());
        doacao.setDataDoacao(LocalDateTime.now());

        return doacao;

    }

}

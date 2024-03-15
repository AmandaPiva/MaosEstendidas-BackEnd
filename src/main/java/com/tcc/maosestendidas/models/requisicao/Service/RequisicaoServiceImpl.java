package com.tcc.maosestendidas.models.requisicao.Service;

import com.tcc.maosestendidas.models.doacao.entity.Doacao;
import com.tcc.maosestendidas.models.doacao.entity.DoacaoRepository;
import com.tcc.maosestendidas.models.pessoa.entity.Pessoa;
import com.tcc.maosestendidas.models.pessoa.entity.PessoaRepository;
import com.tcc.maosestendidas.models.requisicao.DTO.RequisicaoDTO;
import com.tcc.maosestendidas.models.requisicao.DTO.VinculaDoacaoNaRequisicaoDTO;
import com.tcc.maosestendidas.models.requisicao.Entity.Requisicao;
import com.tcc.maosestendidas.models.requisicao.Entity.RequisicaoRepository;
import com.tcc.maosestendidas.models.requisicao.Entity.StatusRequisicao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RequisicaoServiceImpl implements RequisicaoService{

    @Autowired
    private RequisicaoRepository requisicaoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private DoacaoRepository doacaoRepository;

    @Override
    public List<Requisicao> listarRequisicoes() {
        return requisicaoRepository.findAll();
    }

    @Override
    public Requisicao buscarRequisicaoPeloId(String idRequisicao) {
        Optional<Requisicao> requisicao = requisicaoRepository.findById(idRequisicao);
        if(requisicao.isEmpty()) throw new RuntimeException("Requisição não encontrada pelo id informado");

        return requisicao.get();
    }

    @Override
    public List<Requisicao> buscarRequisicaoPelaPessoa(String idPessoa) {
        Optional<Pessoa> pessoa = pessoaRepository.findById(idPessoa);
        if(pessoa.isEmpty()) throw new RuntimeException("Pessoa não encontrada pelo id informado");

        return requisicaoRepository.findByPessoaDonataria(pessoa.get());
    }

    //SERVICO QUE FILTRA OS STATUS DAS REQUISICOES E TRAS A REQUISICAO DE ACORDO COM OS STATUS PASSADO COMO ARGUMENTO
    @Override
    public List<Requisicao> buscarRequisicaoPeloStatus(StatusRequisicao statusRequisicao) {
        return requisicaoRepository.findAll().stream()
                .filter(requisicao -> requisicao.getStatusRequisicao() == statusRequisicao)
                .collect(Collectors.toList());
    }

    @Override
    public Requisicao criaRequisicao(RequisicaoDTO dto) {
        Requisicao requisicao = converteDtoParaRequisicao(dto);
        requisicaoRepository.save(requisicao);

        return requisicao;
    }

    private Requisicao converteDtoParaRequisicao(RequisicaoDTO dto){
        Requisicao requisicao = new Requisicao();

        Optional<Pessoa> pessoa = pessoaRepository.findById(dto.getPessoaDonataria());
        if(pessoa.isEmpty()) throw new RuntimeException("Pessoa não encontrada pelo id informado");

        requisicao.setTituloRequisicao(dto.getTituloRequisicao());
        requisicao.setDescricaoRequisicao(dto.getDescricaoRequisicao());
        requisicao.setPessoaDonataria(pessoa.get());
        requisicao.setStatusRequisicao(dto.getStatusRequisicao());
        requisicao.setDataRequisicao(LocalDateTime.now());


        return requisicao;
    }

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
    public Requisicao updateStatusRequisicao(String idRequisicao, StatusRequisicao novoStatus){
        Optional<Requisicao> requisicaoOptional = requisicaoRepository.findById(idRequisicao);
        if(requisicaoOptional.isEmpty()) {
            throw new RuntimeException("Requisição não encontrada pelo id informado");
        }

        Requisicao requisicao = requisicaoOptional.get();
        requisicao.setStatusRequisicao(novoStatus);
        requisicao.setDataRequisicao(LocalDateTime.now());

        return requisicaoRepository.save(requisicao);
    }

    @Override
    public Requisicao updateRequisicao(RequisicaoDTO dto, String idRequisicao) {
        Optional<Requisicao> requisicao = requisicaoRepository.findById(idRequisicao);
        if(requisicao.isEmpty()) throw new RuntimeException("Requisição não encontrada pelo id informado");

        Requisicao updateRequisicao = requisicao.get();

        updateRequisicao.setTituloRequisicao(dto.getTituloRequisicao());
        updateRequisicao.setDescricaoRequisicao(dto.getDescricaoRequisicao());
        updateRequisicao.setStatusRequisicao(dto.getStatusRequisicao());


        return updateRequisicao;
    }

    @Override
    public void deleteRequisicao(String idRequisicao) {
        Optional<Requisicao> requisicao = requisicaoRepository.findById(idRequisicao);
        if(requisicao.isEmpty()) throw new RuntimeException("Requisição não encontrada pelo id informado");

        Requisicao requisicao1 = requisicao.get();
        requisicaoRepository.delete(requisicao1);
    }
}

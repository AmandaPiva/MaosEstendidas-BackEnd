package com.tcc.maosestendidas.models.pessoa.service;

import com.tcc.maosestendidas.models.pessoa.DTO.PessoaDTO;
import com.tcc.maosestendidas.models.pessoa.entity.Pessoa;
import com.tcc.maosestendidas.models.pessoa.entity.PessoaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class PessoaServiceImpl implements PessoaService{

    @Autowired
    private PessoaRepository pessoaRepository;

    @Override
    public List<Pessoa> listaPessoas() {
        return pessoaRepository.findAll();
    }

    @Override
    public Pessoa buscaPessoaPeloEmail(String emailPessoa) {
        Optional<Pessoa> pessoa = pessoaRepository.findByEmailPessoa(emailPessoa);
        if(pessoa.isEmpty()) throw new RuntimeException("Pessoa não encontrada pelo email informado");

        return pessoa.get();
    }

    @Override
    public Pessoa buscaPessoaPeloCpf(String cpfPessoa) {
        Optional<Pessoa> pessoa = pessoaRepository.findByCpfPessoa(cpfPessoa);
        if(pessoa.isEmpty()) throw new RuntimeException("Pessoa não encontrada pelo cpf informado");

        return pessoa.get();
    }

    @Override
    @Transactional
    public Pessoa criaPessoa(PessoaDTO dto) {
        Pessoa pessoa = converteDtoParaPessoa(dto);
        pessoaRepository.save(pessoa);

        return pessoa;
    }

    private Pessoa converteDtoParaPessoa(PessoaDTO dto){
        Pessoa pessoa = new Pessoa();

        pessoa.setNomePessoa(dto.getNomePessoa());
        pessoa.setEmailPessoa(dto.getEmailPessoa());
        pessoa.setCpfPessoa(dto.getCpfPessoa());




        pessoa.setDataNascimentoPessoa(dto.getDataNascimentoPessoa());
        return pessoa;
    }

    @Override
    public Pessoa updatePessoa(PessoaDTO dto, String idPessoa) {
        Optional<Pessoa> pessoa = pessoaRepository.findById(idPessoa);
        if(pessoa.isEmpty()) throw new RuntimeException("Pessoa não encontrada pelo id informado");

        Pessoa updatePessoa = pessoa.get();

        updatePessoa.setCpfPessoa(dto.getCpfPessoa());
        updatePessoa.setEmailPessoa(dto.getEmailPessoa());
        updatePessoa.setNomePessoa(dto.getNomePessoa());
        updatePessoa.setDataNascimentoPessoa(dto.getDataNascimentoPessoa());

        return null;
    }
}

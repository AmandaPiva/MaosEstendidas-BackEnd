package com.tcc.maosestendidas.models.pessoa.service;

import com.tcc.maosestendidas.models.endereco.Entity.Endereco;
import com.tcc.maosestendidas.models.endereco.Entity.EnderecoRepository;
import com.tcc.maosestendidas.models.pessoa.DTO.PessoaDTO;
import com.tcc.maosestendidas.models.pessoa.entity.Pessoa;
import com.tcc.maosestendidas.models.pessoa.entity.PessoaRepository;
import com.tcc.maosestendidas.models.pessoa.entity.PessoaRole;
import com.tcc.maosestendidas.models.pessoa.entity.PessoaRoleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class PessoaServiceImpl implements PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private PessoaRoleRepository pessoaRoleRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Override
    public List<Pessoa> listaPessoas() {
        return pessoaRepository.findAll();
    }

    @Override
    public Pessoa buscaPessoaPeloEmail(String emailPessoa) {
        Optional<Pessoa> pessoa = pessoaRepository.findByEmailPessoa(emailPessoa);
        if (pessoa.isEmpty()) throw new RuntimeException("Pessoa não encontrada pelo email informado");

        return pessoa.get();
    }

    @Override
    public Optional<Pessoa> buscaPessoaPeloEmailOptional(String email) {
        return pessoaRepository.findByEmailPessoa(email);
    }

    @Override
    public Pessoa uploadImagem(String idPessoa, MultipartFile imagem) throws IOException {
        Optional<Pessoa> pessoa = pessoaRepository.findById(idPessoa);
        if (pessoa.isEmpty()) throw new RuntimeException("Pessoa não encontrada pelo id informado");

        Pessoa pessoa1 = pessoa.get();
        pessoa1.setImagemPerfil(imagem.getBytes());
        return pessoaRepository.save(pessoa1);
    }

    @Override
    public Pessoa buscaPessoaPeloDocumento(String documentoPessoa) {
        Optional<Pessoa> pessoa = pessoaRepository.findByDocumentoPessoa(documentoPessoa);
        if (pessoa.isEmpty()) throw new RuntimeException("Pessoa não encontrada pelo documento informado");

        return pessoa.get();
    }

    @Override
    public Pessoa buscaPessoaPeloCelular(String celular) {
        Optional<Pessoa> pessoa = pessoaRepository.findByCelular(celular);
        if (pessoa.isEmpty()) throw new RuntimeException("Pessoa não encontrada pelo celular informado");

        return pessoa.get();
    }

    @Override
    @Transactional
    public Pessoa criaPessoa(PessoaDTO dto) {
        Pessoa pessoa = converteDtoParaPessoa(dto);
        pessoaRepository.save(pessoa);

        return pessoa;
    }

    @Override
    @Transactional
    public Pessoa criaPessoa(Pessoa pessoa) {
        pessoaRepository.save(pessoa);
        return pessoa;
    }

    private Pessoa converteDtoParaPessoa(PessoaDTO dto) {
        Pessoa pessoa = new Pessoa();

        Optional<PessoaRole> pessoaRole = pessoaRoleRepository.findById(dto.getRolePessoa());
        Optional<Endereco> endereco = enderecoRepository.findById(dto.getEndereco());

        pessoa.setNomePessoa(dto.getNomePessoa());
        pessoa.setEmailPessoa(dto.getEmailPessoa());
        pessoa.setDocumentoPessoa(dto.getDocumentoPessoa());
        pessoa.setCelular(dto.getCelular());
        pessoa.setRolePessoa(pessoaRole.get());
        pessoa.setSenhaPessoa(geraSenhaHash(dto.getSenhaPessoa()));
        pessoa.setEndereco(endereco.get());
        pessoa.setDataNascimentoPessoa(dto.getDataNascimentoPessoa());
        return pessoa;
    }

    //senha de gerar hash para criptografar a senha
    private String geraSenhaHash(String senha) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(senha);
    }


    @Override
    public Pessoa updatePessoa(PessoaDTO dto, String idPessoa) {
        Optional<Pessoa> pessoa = pessoaRepository.findById(idPessoa);
        if (pessoa.isEmpty()) throw new RuntimeException("Pessoa não encontrada pelo id informado");

        Pessoa updatePessoa = pessoa.get();

        updatePessoa.setDocumentoPessoa(dto.getDocumentoPessoa());
        updatePessoa.setEmailPessoa(dto.getEmailPessoa());
        updatePessoa.setCelular(dto.getCelular());
        updatePessoa.setNomePessoa(dto.getNomePessoa());
        updatePessoa.setDataNascimentoPessoa(dto.getDataNascimentoPessoa());

        pessoaRepository.save(updatePessoa);

        return updatePessoa;
    }

//    @Override
//    public Pessoa updateSenha(PessoaDTO dto, String email) {
//        Optional<Pessoa> pessoa = pessoaRepository.findByEmailPessoa(email);
//        if(pessoa.isEmpty()) throw new RuntimeException("Pessoa não encontrada pelo email informado");
//
//        Pessoa updateSenhaPessoa = pessoa.get();
//
//        updateSenhaPessoa.setSenhaPessoa(geraSenhaHash(dto.getSenhaPessoa()));
//
//        pessoaRepository.save(updateSenhaPessoa);
//
//        return updateSenhaPessoa;
//    }

    @Override
    public Pessoa updateSenha(String senha, String email) {
        Optional<Pessoa> pessoa = pessoaRepository.findByEmailPessoa(email);
        if (pessoa.isEmpty()) throw new RuntimeException("Pessoa não encontrada pelo email informado");

        Pessoa updateSenhaPessoa = pessoa.get();

        updateSenhaPessoa.setSenhaPessoa(geraSenhaHash(senha));

        pessoaRepository.save(updateSenhaPessoa);

        return updateSenhaPessoa;
    }


    @Override
    public List<Pessoa> listarPessoasPelaRole(String rolePessoa) {
        Optional<PessoaRole> pessoaRole = pessoaRoleRepository.findByRolePessoa(rolePessoa);
        if (pessoaRole.isEmpty()) throw new RuntimeException("Não foi encontrado pessoas com esta role");

        return pessoaRepository.findByRolePessoa(pessoaRole.get());
    }

    //CRIPTOGRAFIA DE SENHAS
    @Override
    public Boolean comparaSenhas(String senhaCriptografada, String senha) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.matches(senha, senhaCriptografada);
    }

    //MÉTODO PARA LOGAR DE ACORDO COM O EMAIL PASSADO PELO USUÁRIO
    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String email) {
                return pessoaRepository.findByEmailPessoa(email)
                        .orElseThrow(() -> new RuntimeException("Usuário não econtrado"));
            }
        };
    }


}

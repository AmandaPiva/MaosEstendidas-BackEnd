package com.tcc.maosestendidas.Autenticacao.Service;

import com.tcc.maosestendidas.JWT.DTO.JwtAuthResponseDTO;
import com.tcc.maosestendidas.JWT.Service.JwtService;
import com.tcc.maosestendidas.models.pessoa.DTO.PessoaLoginDTO;
import com.tcc.maosestendidas.models.pessoa.entity.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AutenticacaoServiceImpl implements AutenticacaoService{
    @Autowired
    private JwtService jwtService;

    @Autowired
    private PessoaRepository pessoaRepository;


    @Autowired
    private final AuthenticationManager authenticationManager;

    @Override
    public JwtAuthResponseDTO assina(PessoaLoginDTO dto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getSenha()));
                var user = pessoaRepository.findByEmailPessoa(dto.getEmail()).orElseThrow(
                        ()-> new RuntimeException("Email ou senha inválidos"));

        HashMap<String, String> claims = new HashMap<String, String>();

        //aplicando essas regras no servço
        claims.put("role", user.getRolePessoa().getRolePessoa() );
        claims.put("email", user.getEmailPessoa());

        var jwt = jwtService.generateToken(claims, user);

        JwtAuthResponseDTO resposta = new JwtAuthResponseDTO();

        resposta.setToken(jwt);
        resposta.setEmail(user.getEmailPessoa());
        resposta.setRole(user.getRolePessoa().getRolePessoa());

        return resposta;
    }
}

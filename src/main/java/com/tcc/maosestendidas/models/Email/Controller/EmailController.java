package com.tcc.maosestendidas.models.Email.Controller;

import com.tcc.maosestendidas.models.Email.DTO.EmailDTO;
import com.tcc.maosestendidas.models.pessoa.DTO.PessoaDTO;
import com.tcc.maosestendidas.models.pessoa.entity.PessoaRepository;
import com.tcc.maosestendidas.models.pessoa.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/email")
public class EmailController {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private PessoaService pessoaService;

    @PostMapping("/send-password")
    public String sendPassword(@RequestBody EmailDTO emailDTO, String senha ) {
        try {
            try{
                pessoaService.updateSenha(emailDTO.getNewPassword(), emailDTO.getEmail());
            } catch(Exception e){
                return "Pessoa não encontrada pelo email";
            }

            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("thaisxavierpires@gmail.com");
            message.setTo(emailDTO.getEmail());
            message.setSubject("Sua nova senha");
            message.setText("Sua nova senha é: " + emailDTO.getNewPassword());
            mailSender.send(message);
            return "Email enviado com sucesso!";
        } catch (Exception e) {
            return "Erro ao enviar email: " + e.getMessage();
        }
    }

}

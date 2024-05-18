package com.tcc.maosestendidas.models.Email.Controller;

import com.tcc.maosestendidas.models.Email.DTO.EmailDTO;
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

    @PostMapping("/send-password")
    public String sendPassword(@RequestBody EmailDTO emailDTO) {
        try {
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

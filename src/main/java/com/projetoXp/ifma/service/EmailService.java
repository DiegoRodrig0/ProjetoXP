package com.projetoXp.ifma.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    public void enviarEmail(String para, String assunto, String texto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("matheuscarvalho_5@hotmail.com");
        message.setTo(para);
        message.setSubject(assunto);
        message.setText(texto);
        emailSender.send(message);
    }
}

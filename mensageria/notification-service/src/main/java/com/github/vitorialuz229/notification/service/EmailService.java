package com.github.vitorialuz229.notification.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    private final JavaMailSender mailSender;
    private String usernameEmail;

    public EmailService(JavaMailSender mailSender, @Value("${spring.mail.username}") String usernameEmail) {
        this.mailSender = mailSender;
        this.usernameEmail = usernameEmail;
    }

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void enviarEmail(String to, String assunto, String corpoTexto) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            String corpoHtml = """
                <html>
                <body style="font-family: Arial, sans-serif; background-color: #f8f8f8; padding: 20px;">
                    <div style="max-width: 600px; margin: auto; background-color: white; padding: 20px; border-radius: 10px;">
                        <h2 style="color: #4CAF50;">Atualização do Pedido</h2>
                        <p style="font-size: 16px;">%s</p>
                        <p style="font-size: 14px; color: gray;">Este é um e-mail automático. Por favor, não responda.</p>
                    </div>
                </body>
                </html>
                """.formatted(corpoTexto);

            helper.setFrom(usernameEmail);
            helper.setTo(to);
            helper.setSubject(assunto);
            helper.setText(corpoHtml, true);

            mailSender.send(message);
            System.out.println("E-mail HTML enviado para: " + to);

        } catch (MessagingException e) {
            System.err.println("Erro ao enviar e-mail: " + e.getMessage());
        }
    }
}

package com.springboot.SpringBackend.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailerService {
    private final JavaMailSender javaMailSender;

    public MailerService(JavaMailSender mailer) {
        this.javaMailSender = mailer;
    }

    public void sendMail(String toEmail, String subject, String message) {

        var mailMessage = new SimpleMailMessage();

        mailMessage.setTo(toEmail);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        mailMessage.setFrom("SigmaCOS301@gmail.com");
        javaMailSender.send(mailMessage);
    }
}

package com.springboot.SpringBackend.service;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;

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



    public void sendMailWithInlineResources(String to, String subject, String fileToAttach, String messageEmail) {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {

                mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
                mimeMessage.setFrom(new InternetAddress("SigmaCOS301@gmail.com"));
                mimeMessage.setSubject(subject);

                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
                helper.setText("<html><body><p>" + messageEmail + "</p><img src='cid:personDetected' alt='PersonImage'></body></html>", true);

                FileSystemResource res = new FileSystemResource(new File(fileToAttach));
                helper.addInline("personDetected", res);
            }
        };

        try {
            javaMailSender.send(preparator);
        }
        catch (MailException ex) {
            // simply log it and go on...
            System.err.println(ex.getMessage());
        }
    }
}

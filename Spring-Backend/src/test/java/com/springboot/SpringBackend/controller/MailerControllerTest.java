package com.springboot.SpringBackend.controller;

import com.springboot.SpringBackend.service.MailerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MailerControllerTest {

    @Autowired
    private MailerService emailService;

    @Test
    void sendmailGrey() {
        emailService.sendMail(
                "SigmaCOS301@gmail.com",
                "Test subject",
                "Test for Suspicious email"
        );
    }

    @Test
    void sendmailBlack() {
        emailService.sendMail(
                "SigmaCOS301@gmail.com",
                "Test subject",
                "Test for Threat email"
        );
    }
}

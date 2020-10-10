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
    void sendEmail() {
        emailService.sendMail(
                "SigmaCOS301@gmail.com",
                "Test",
                "Testing email!"
        );
    }
}

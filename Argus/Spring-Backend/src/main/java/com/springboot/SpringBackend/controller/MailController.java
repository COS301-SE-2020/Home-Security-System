package com.springboot.SpringBackend.controller;

import com.springboot.SpringBackend.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MailController {
    @Autowired
    private EmailService emailSender;

    @GetMapping(value = "/sendmail/{emailAddress}")
    public String sendmail(@PathVariable("emailAddress") String email) {
        String x = "sent";
        emailSender.sendMail(
                email,
                "Argus System Alert",
                "An unknown person was detected on the property"
        );

        return x;
    }
}

package com.springboot.SpringBackend.controller;

import com.springboot.SpringBackend.service.MailerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MailerController {
    @Autowired
    private MailerService emailSender;

    // Usage: get request, http://localhost:8080/springboot/sendmailGrey/{email}
    @GetMapping(value = "/sendmailGrey/{emailAddress}")
    public String sendmailGrey(@PathVariable("emailAddress") String email) {
        String x = "sent";
        emailSender.sendMail(
                email,
                "Argus System Greylist Alert",
                "An unknown person was detected on the Argus system"
        );

        return x;
    }
    // Usage: get request, http://localhost:8080/springboot/sendmailBlack/{email}
    @GetMapping(value = "/sendmailBlack/{emailAddress}")
    public String sendmailBlack(@PathVariable("emailAddress") String email) {
        String x = "sent";
        emailSender.sendMail(
                email,
                "Argus System Blacklist Alert",
                "A person from your blacklist has been detected on the Argus system"
        );

        return x;
    }
}

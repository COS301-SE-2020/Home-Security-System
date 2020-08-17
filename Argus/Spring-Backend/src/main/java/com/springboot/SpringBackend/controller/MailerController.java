package com.springboot.SpringBackend.controller;

import com.springboot.SpringBackend.service.MailerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MailerController {
    private String imagePath = "C:\\Users\\Brad\\Home-Security-System\\Argus\\Angular-Frontend\\src\\assets\\Images\\Argus.png";

    private MailerService emailSender;

    @Autowired
    public MailerController(MailerService mailer) {
        this.emailSender = mailer;
    }

    // Usage: get request, http://localhost:8080/springboot/sendmailGrey/{email}
    // @GetMapping(value = "/sendmailGrey/{emailAddress}")
    public String sendmailGrey(String email) {
        String x = "sent";
        emailSender.sendMail(
                email,
                "Argus System Unknown-list Alert",
                "An unknown person was detected on the Argus system"
        );

        return x;
    }

    // Usage: get request, http://localhost:8080/springboot/sendmailBlack/{email}
    // @GetMapping(value = "/sendmailBlack/{emailAddress}")
    public String sendmailBlack(String email) {
        String x = "sent";
        emailSender.sendMail(
                email,
                "Argus System Threat-list Alert",
                "A person from your blacklist has been detected on the Argus system. Image captured below:"
        );

        return x;
    }

    // Usage: get request, http://localhost:8080/springboot/sendmailGreyAtatchment/{emailAddress}
    // @GetMapping(value = "/sendmailGreyAtatchment/{emailAddress}")
    public String sendWithAttatchGL(String email) {
        String x = "sent";
        emailSender.sendMailWithInlineResources(
                email,
                "ALERT: Argus Unknown-list Detection",
                imagePath,
                "This unknown person has been detected"
        );

        return x;
    }

    // Usage: get request, http://localhost:8080/springboot/sendmailBlackAtatchment/{emailAddress}
    // @GetMapping(value = "/sendmailBlackAtatchment/{emailAddress}")
    public String sendWithAttatchBL(String email) {
        String x = "sent";
        emailSender.sendMailWithInlineResources(
                email,
                "WARNING: Argus Threat-list Detection",
                imagePath,
                "This person from your blacklist has been detected. Image captured below:"
        );

        return x;
    }

    public void setImagePath(String imgPathVar) { imagePath = imgPathVar; }
}

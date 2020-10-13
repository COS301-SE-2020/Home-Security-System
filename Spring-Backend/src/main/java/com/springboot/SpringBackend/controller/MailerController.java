package com.springboot.SpringBackend.controller;

import com.springboot.SpringBackend.service.MailerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MailerController {
    @Autowired
    private MailerService emailSender;
    private String imagePath = "\\Home-Security-System\\Angular-Frontend\\src\\assets\\Images\\ArgusLogo.png";

    // Usage: get request, http://localhost:8080/sendmailGrey/{email}
    // @GetMapping(value = "/sendmailGrey/{emailAddress}")
    public String sendmailGrey(String email, String name, String date, String time) {
        String x = "sent";
        emailSender.sendMail(
                email,
                "ALERT: New " + name + " person detected on ARGUS",
                "Suspicious person detected on " + date + " at " + time + "."
        );

        return x;
    }
    // Usage: get request, http://localhost:8080/sendmailBlack/{email}
    // @GetMapping(value = "/sendmailBlack/{emailAddress}")
    public String sendmailBlack(String email, String name, String date, String time) {
        String x = "sent";
        emailSender.sendMail(
                email,
                "WARNING: Threat Detected on ARGUS",
                name + " detected on " + date + " at " + time + "."
        );

        return x;
    }
    // Usage: get request, http://localhost:8080/sendmailBlackAtatchment/{emailAddress}
    // @GetMapping(value = "/sendmailBlackAtatchment/{emailAddress}")
    public String sendWithAttatchBL(String email, String name, String date, String time) {
        String x = "sent";
        emailSender.sendMailWithInlineResources(
                email,
                "WARNING: Threat Detected on ARGUS",
                imagePath,
                name + " detected on " + date + " at " + time + "."
        );

        return x;
    }
    // Usage: get request, http://localhost:8080/sendmailGreyAtatchment/{emailAddress}
    // @GetMapping(value = "/sendmailGreyAtatchment/{emailAddress}")
    public String sendWithAttatchGL(String email, String name, String date, String time) {
        String x = "sent";
        emailSender.sendMailWithInlineResources(
                email,
                "ALERT: New " + name + " person detected on ARGUS",
                imagePath,
                "Suspicious person detected on " + date + " at " + time + "."
        );

        return x;
    }

    public String sendRegistration(String email) {
        String x = "sent";
        emailSender.sendMail(
                email,
                "Registration",
                /*imagePath,*/
                "Welcome to ARGUS! \n\n If you did not use this email to register please contact sigmacos301@gmail.com"
        );

        return x;
    }

    public void setImagePath(String imgPathVar) { imagePath = imgPathVar; }
}

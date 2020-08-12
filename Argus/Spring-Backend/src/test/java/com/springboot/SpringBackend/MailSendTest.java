package com.springboot.SpringBackend;
import com.springboot.SpringBackend.service.MailerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

// A backend test for sending emails

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailSendTest {

    @Autowired
    private MailerService emailService;

    @Test
    public void testEmail() {
        emailService.sendMail(
                "SigmaCOS301@gmail.com",
                "Test subject",
                "Test mail"
        );
    }
}

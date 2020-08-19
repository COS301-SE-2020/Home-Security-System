package com.springboot.SpringBackend.controllerTest;

import com.springboot.SpringBackend.model.Notification;
import com.springboot.SpringBackend.model.User;
import com.springboot.SpringBackend.repository.UserRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NotificationControllerTest {
    private static final String IMAGE_URL = "http://an-imageurl.com/image1.jpg";

    // @Autowired
    // private ImageRepo irepo;
    @Autowired
    private UserRepo repo;
    @Autowired
    private TestRestTemplate restTemplate;

    private String getRootUrl() {
        return "http://localhost:4200/springboot/api/notifications";
    }

    @Test
    public void contextLoads() {

    }

    @Test
    public void testCreateNotification() {
        User u = new User("Photo", "Brad", "Zietsman", "u15228194@tuks.co.za", "Bradford", "123qweASD!", "Admin");
        repo.save(u);
        Notification x = new Notification(IMAGE_URL, "Threat", "Hello", u);
        ResponseEntity<Notification> postResponse = restTemplate.postForEntity(getRootUrl(), x, Notification.class);
        System.out.println(" postResponse -> " + postResponse);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
    }

    @Test
    public void testGetNotificationById() {
        Notification x = restTemplate.getForObject(getRootUrl() + "/1", Notification.class);
        System.out.println(x);
        assertNotNull(x);
    }

    @Test
    public void testGetAllNotifications() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl(), HttpMethod.GET, entity, String.class);
        System.out.println(response);
        assertNotNull(response.getBody());
    }


    @Test
    public void testUpdateNotification() {
        int id = 1;
        Notification x = restTemplate.getForObject(getRootUrl() + id, Notification.class);
        x.setMessage("Updated");
        restTemplate.put(getRootUrl() +  id, x);
        Notification updatedNotification = restTemplate.getForObject(getRootUrl() + id, Notification.class);
        assertNotNull(updatedNotification);
    }

    @Test
    public void testDeleteImage() {
        int id = 2;
        Notification x = restTemplate.getForObject(getRootUrl() + id, Notification.class);
        assertNotNull(x);
        restTemplate.delete(getRootUrl() + id);

        try {
            x = restTemplate.getForObject(getRootUrl() + id, Notification.class);
        } catch (final HttpClientErrorException e) {
            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }
}

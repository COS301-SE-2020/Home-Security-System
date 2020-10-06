/*
package com.springboot.SpringBackend;

import com.springboot.SpringBackend.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    private String getRootUrl() {
        return "http://localhost:4200/springboot/api/users";
    }

    @Test
    public void contextLoads() {

    }

    @Test
    public void testCreateUser() {
        User u = new User();
        u.setProfilePhoto("Photo");
        u.setFname("Brad");
        u.setLname("Zietsman");
        u.setEmail("brad.zietsman@gmail.com");
        u.setUsername("Bradford");
        u.setUserPass("123qweASD!");
        u.setUserRole("Admin");
        u.setNotifySMS(true);
        u.setNotifyEmail(true);
        ResponseEntity<User> postResponse = restTemplate.postForEntity(getRootUrl(), u, User.class);
        System.out.println(" postResponse -> " + postResponse);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
    }

    @Test
    public void testGetUserById() {
        User u = restTemplate.getForObject(getRootUrl() + "/1", User.class);
        System.out.println(u);
        assertNotNull(u);
    }

    @Test
    public void testGetAllUsers() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl(), HttpMethod.GET, entity, String.class);
        System.out.println(response);
        assertNotNull(response.getBody());
    }


    @Test
    public void testUpdateUser() {
        int id = 1;
        User u = restTemplate.getForObject(getRootUrl() + id, User.class);
        u.setUserRole("Basic");
        u.setNotifyEmail(false);
        restTemplate.put(getRootUrl() +  id, u);
        User updatedUser = restTemplate.getForObject(getRootUrl() + id, User.class);
        assertNotNull(updatedUser);
    }

    @Test
    public void testDeleteUser() {
        int id = 2;
        User u = restTemplate.getForObject(getRootUrl() + id, User.class);
        assertNotNull(u);
        restTemplate.delete(getRootUrl() + id);

        try {
            u = restTemplate.getForObject(getRootUrl() + id, User.class);
        } catch (final HttpClientErrorException e) {
            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }
}
*/

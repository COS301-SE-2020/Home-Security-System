/*
package com.springboot.SpringBackend;

import com.springboot.SpringBackend.model.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    private String getRootUrl() {
        return "http://localhost:4200/springboot/api/people";
    }

    @Test
    public void contextLoads() {

    }

    @Test
    public void testCreatePerson() {
        Person x = new Person("Photo", "John", "Fish", "Grey");
        ResponseEntity<Person> postResponse = restTemplate.postForEntity(getRootUrl(), x, Person.class);
        System.out.println(" postResponse -> " + postResponse);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
    }

    @Test
    public void testGetPersonById() {
        Person x = restTemplate.getForObject(getRootUrl() + "/1", Person.class);
        System.out.println(x);
        assertNotNull(x);
    }

    @Test
    public void testGetAllPeople() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl(), HttpMethod.GET, entity, String.class);
        System.out.println(response);
        assertNotNull(response.getBody());
    }


    @Test
    public void testUpdatePerson() {
        int id = 1;
        Person x = restTemplate.getForObject(getRootUrl() + id, Person.class);
        x.setPersonImg("Image");
        x.setPersonListed("Black");
        restTemplate.put(getRootUrl() +  id, x);
        Person updatedPerson = restTemplate.getForObject(getRootUrl() + id, Person.class);
        assertNotNull(updatedPerson);
    }

    @Test
    public void testDeletePerson() {
        int id = 2;
        Person x = restTemplate.getForObject(getRootUrl() + id, Person.class);
        assertNotNull(x);
        restTemplate.delete(getRootUrl() + id);

        try {
            x = restTemplate.getForObject(getRootUrl() + id, Person.class);
        } catch (final HttpClientErrorException e) {
            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }
}
*/

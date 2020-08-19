package com.springboot.SpringBackend.controllerTest;

import com.springboot.SpringBackend.model.Face;
import com.springboot.SpringBackend.model.Person;
import com.springboot.SpringBackend.repository.PersonRepo;
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
public class FaceControllerTest {
    private static final String IMAGE_URL = "http://an-imageurl.com/image1.jpg";

    // @Autowired
    // private ImageRepo irepo;
    @Autowired
    private PersonRepo repo;
    @Autowired
    private TestRestTemplate restTemplate;

    private String getRootUrl() {
        return "http://localhost:4200/springboot/api/faces";
    }

    @Test
    public void contextLoads() {

    }

    @Test
    public void testCreateFace() {
        Person person = new Person(IMAGE_URL);
        repo.save(person);
        Face x = new Face();
        x.setPerson(person);
        x.setFeatures("Face");
        ResponseEntity<Face> postResponse = restTemplate.postForEntity(getRootUrl(), x, Face.class);
        System.out.println(" postResponse -> " + postResponse);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
    }

    @Test
    public void testGetFaceById() {
        Face x = restTemplate.getForObject(getRootUrl() + "/1", Face.class);
        System.out.println(x);
        assertNotNull(x);
    }

    @Test
    public void testGetAllFaces() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl(), HttpMethod.GET, entity, String.class);
        System.out.println(response);
        assertNotNull(response.getBody());
    }


    @Test
    public void testUpdateFace() {
        int id = 1;
        Person person = new Person(IMAGE_URL);
        repo.save(person);
        Face x = restTemplate.getForObject(getRootUrl() + id, Face.class);
        x.setPerson(person);
        x.setFeatures("Feature");
        restTemplate.put(getRootUrl() +  id, x);
        Face updatedFace = restTemplate.getForObject(getRootUrl() + id, Face.class);
        assertNotNull(updatedFace);
    }

    @Test
    public void testDeleteFace() {
        int id = 2;
        Face x = restTemplate.getForObject(getRootUrl() + id, Face.class);
        assertNotNull(x);
        restTemplate.delete(getRootUrl() + id);

        try {
            x = restTemplate.getForObject(getRootUrl() + id, Face.class);
        } catch (final HttpClientErrorException e) {
            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }
}

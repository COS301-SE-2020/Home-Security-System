/*
package com.springboot.SpringBackend.controllerTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.springboot.SpringBackend.SpringBackendApplication;
import com.springboot.SpringBackend.model.Image;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ImageControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    private String getRootUrl() {
        return "http://localhost:4200/springboot/api/images";
    }

    @Test
    public void contextLoads() {

    }

    @Test
    public void testCreateImage() {
        Image img = new Image();
        img.setPhoto("Photo");
        ResponseEntity<Image> postResponse = restTemplate.postForEntity(getRootUrl(), img, Image.class);
        System.out.println(" postResponse -> " + postResponse);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
    }

    @Test
    public void testGetImageById() {
        Image img = restTemplate.getForObject(getRootUrl() + "/1", Image.class);
        System.out.println(img);
        assertNotNull(img);
    }

    @Test
    public void testGetAllImages() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl(), HttpMethod.GET, entity, String.class);
        System.out.println(response);
        assertNotNull(response.getBody());
    }


	@Test
	public void testUpdateImage() {
		int id = 1;
		Image img = restTemplate.getForObject(getRootUrl() + id, Image.class);
		img.setPhoto("Image");
		restTemplate.put(getRootUrl() +  id, img);
		Image updatedImg = restTemplate.getForObject(getRootUrl() + id, Image.class);
		assertNotNull(updatedImg);
	}

	@Test
	public void testDeleteImage() {
		int id = 2;
		Image img = restTemplate.getForObject(getRootUrl() + id, Image.class);
		assertNotNull(img);
		restTemplate.delete(getRootUrl() + id);

		try {
			img = restTemplate.getForObject(getRootUrl() + id, Image.class);
		} catch (final HttpClientErrorException e) {
			assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
		}
	}
}
*/

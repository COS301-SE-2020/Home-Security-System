package com.springboot.SpringBackend.controllerTest;

import com.springboot.SpringBackend.model.Vehicle;
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
public class VehicleControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    private String getRootUrl() {
        return "http://localhost:4200/springboot/api/vehicles";
    }

    @Test
    public void contextLoads() {

    }

    @Test
    public void testCreateVehicle() {
        Vehicle x = new Vehicle("Photo", "CW36FWGP", "Hyundi", "i10",  "White");
        ResponseEntity<Vehicle> postResponse = restTemplate.postForEntity(getRootUrl(), x, Vehicle.class);
        System.out.println(" postResponse -> " + postResponse);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
    }

    @Test
    public void testGetVehicleById() {
        Vehicle x = restTemplate.getForObject(getRootUrl() + "/1", Vehicle.class);
        System.out.println(x);
        assertNotNull(x);
    }

    @Test
    public void testGetAllVehicles() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl(), HttpMethod.GET, entity, String.class);
        System.out.println(response);
        assertNotNull(response.getBody());
    }


    @Test
    public void testUpdateVehicle() {
        int id = 1;
        Vehicle x = restTemplate.getForObject(getRootUrl() + id, Vehicle.class);
        x.setVehicleListed("White");
        restTemplate.put(getRootUrl() +  id, x);
        Vehicle updatedVehicle = restTemplate.getForObject(getRootUrl() + id, Vehicle.class);
        assertNotNull(updatedVehicle);
    }

    @Test
    public void testDeleteVehicle() {
        int id = 2;
        Vehicle x = restTemplate.getForObject(getRootUrl() + id, Vehicle.class);
        assertNotNull(x);
        restTemplate.delete(getRootUrl() + id);

        try {
            x = restTemplate.getForObject(getRootUrl() + id, Vehicle.class);
        } catch (final HttpClientErrorException e) {
            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }
}

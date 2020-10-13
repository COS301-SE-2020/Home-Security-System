package com.springboot.SpringBackend.controller;

import com.springboot.SpringBackend.model.Camera;
import com.springboot.SpringBackend.model.Network;

import com.springboot.SpringBackend.repository.CameraRepo;
import com.springboot.SpringBackend.repository.NetworkRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CameraControllerTest {

    @Autowired
    private CameraRepo camRepo;

    @Autowired
    private NetworkRepo netRepo;

    /*@Autowired
    private TestRestTemplate restTemplate;*/

    /*private String getRootUrl() {
        return "http://localhost:4200/api/cameras";
        //return "http://sigma-argus.herokuapp.com/api/cameras";
    }*/

    @Test
    void getAllCameras() {
        Network net = new Network("Network1");
        Network savedNetwork = netRepo.save(net);
        Camera cam1 = new Camera("URL1",savedNetwork);
        camRepo.save(cam1);
        Camera cam2 = new Camera("URL2",savedNetwork);
        camRepo.save(cam2);
        List<Camera> list = camRepo.findAll();
        assertTrue(list.size() > 0);

        /*HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl(), HttpMethod.GET, entity, String.class);
        System.out.println(response);
        assertNotNull(response.getBody());*/
    }

    @Test
    void getCameraById() {
        Network net = new Network("TestNetwork1");
        Network savedNetwork = netRepo.save(net);
        Camera cam = new Camera("SomeURL", savedNetwork);
        Camera savedCamera = camRepo.save(cam);
        Optional<Camera> findCam = camRepo.findById(savedCamera.getCameraId());
        assertNotNull(findCam);
        if(findCam.isPresent()) {
            assertEquals("SomeURL", findCam.get().getServerURL());
            assertEquals("TestNetwork1", findCam.get().getNetwork().getNetName());
        }
        /*Camera cam = restTemplate.getForObject(getRootUrl() + "/1", Camera.class);
        assertNotNull(cam);*/
    }

    @Test
    void addCamera() {
        Network net = new Network("RaspberyPi4");
        Network savedNetwork = netRepo.save(net);
        Camera cam = new Camera("SomeURL", savedNetwork);
        Camera savedCam = camRepo.save(cam);
        assertNotNull(savedCam);

        /*Camera cam = new Camera("SomeURL",net);
        ResponseEntity<Camera> postResponse = restTemplate.postForEntity(getRootUrl(), cam, Camera.class);
        System.out.println(" postResponse -> " + postResponse);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());*/
    }

    @Test
    void editCamera() {
        Network net = new Network("TestNetwork3");
        Network savedNetwork = netRepo.save(net);
        Camera cam = new Camera("SomeURL", savedNetwork);
        Camera savedCam = camRepo.save(cam);
        assertEquals("SomeURL", savedCam.getServerURL());
        savedCam.setServerURL("UpdatedCamera");
        savedCam = camRepo.save(savedCam);
        assertEquals("UpdatedCamera", savedCam.getServerURL());

        /*Camera cam = restTemplate.getForObject(getRootUrl() + "/1", Camera.class);
        cam.setServerURL("Basic");
        restTemplate.put(getRootUrl() +  "/1", cam);
        Camera updatedUser = restTemplate.getForObject(getRootUrl() + "/1", Camera.class);
        assertNotNull(updatedUser);*/
    }

    @Test
    void deleteCamera() {
        Network net = new Network("TestNetwork4");
        Network savedNetwork = netRepo.save(net);
        Camera cam = new Camera("SomeURL", savedNetwork);
        Camera savedCam = camRepo.save(cam);

        boolean existsBeforeDelete = camRepo.findById(savedCam.getCameraId()).isPresent();
        assertTrue(existsBeforeDelete);
        camRepo.deleteById(savedCam.getCameraId());
        boolean existsAfterDelete = camRepo.findById(savedCam.getCameraId()).isPresent();
        assertFalse(existsAfterDelete);

        /*Camera cam = restTemplate.getForObject(getRootUrl() + "/1", Camera.class);
        assertNotNull(cam);
        restTemplate.delete(getRootUrl() + "/1");

        try {
            cam = restTemplate.getForObject(getRootUrl() + "/1", Camera.class);
        } catch (final HttpClientErrorException e) {
            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }*/
    }
}

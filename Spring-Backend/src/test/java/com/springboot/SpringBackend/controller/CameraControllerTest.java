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

    /*private String getRootUrl() {
        return "http://localhost:4200/api/cameras";
        //return "http://sigma-argus.herokuapp.com/api/cameras";
    }*/

    @Test
    void getAllCameras() {
        Network net = new Network("TestNetwork", "+27833991336");
        Network savedNetwork = netRepo.save(net);
        Camera cam1 = new Camera("URLOne",savedNetwork);
        camRepo.save(cam1);
        Camera cam2 = new Camera("URLTwo",savedNetwork);
        camRepo.save(cam2);
        List<Camera> list = camRepo.findAll();
        assertTrue(list.size() > 0);
    }

    @Test
    void getCameraById() {
        Network net = new Network("TestNetwork", "+27833991336");
        Network savedNetwork = netRepo.save(net);
        Camera cam = new Camera("SomeURL", savedNetwork);
        Camera savedCamera = camRepo.save(cam);
        Optional<Camera> findCam = camRepo.findById(savedCamera.getCameraId());
        assertNotNull(findCam);
        findCam.ifPresent(camera -> {
            assertEquals("SomeURL", camera.getServerURL());
            assertEquals("TestNetwork", camera.getNetwork().getNetName());
        });
    }

    @Test
    void addCamera() {
        Network net = new Network("TestNetwork", "+27833991336");
        Network savedNetwork = netRepo.save(net);
        Camera cam = new Camera("SomeURL", savedNetwork);
        Camera savedCam = camRepo.save(cam);
        assertNotNull(savedCam);
    }

    @Test
    void editCamera() {
        Network net = new Network("TestNetwork", "+27833991336");
        Network savedNetwork = netRepo.save(net);
        Camera cam = new Camera("SomeURL", savedNetwork);
        Camera savedCam = camRepo.save(cam);
        assertEquals("SomeURL", savedCam.getServerURL());
        savedCam.setServerURL("UpdatedCamera");
        savedCam = camRepo.save(savedCam);
        assertEquals("UpdatedCamera", savedCam.getServerURL());
    }

    @Test
    void deleteCamera() {
        Network net = new Network("TestNetwork", "+27833991336");
        Network savedNetwork = netRepo.save(net);
        Camera cam = new Camera("SomeURL", savedNetwork);
        Camera savedCam = camRepo.save(cam);

        boolean existsBeforeDelete = camRepo.findById(savedCam.getCameraId()).isPresent();
        assertTrue(existsBeforeDelete);
        camRepo.deleteById(savedCam.getCameraId());
        boolean existsAfterDelete = camRepo.findById(savedCam.getCameraId()).isPresent();
        assertFalse(existsAfterDelete);
    }
}

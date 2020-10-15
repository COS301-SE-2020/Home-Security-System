package com.springboot.SpringBackend.repository;

import com.springboot.SpringBackend.model.Camera;
import com.springboot.SpringBackend.model.Network;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CameraRepoTest {

    @Autowired
    private CameraRepo camRepo;

    @Autowired
    private NetworkRepo netRepo;

    @Test
    public void testPersistence() {
        Network net = new Network("TestNetwork", "+27833991336");
        netRepo.save(net);

        Camera cam = new Camera("SomeURL",net);
        camRepo.save(cam);

        assertNotNull(cam.getCameraId());
        Optional<Camera> newCam = camRepo.findById(cam.getCameraId());
        newCam.ifPresent(camera -> {
            assertEquals("SomeURL", camera.getServerURL());
            assertEquals("TestNetwork", camera.getNetwork().getNetName());
        });
    }
}

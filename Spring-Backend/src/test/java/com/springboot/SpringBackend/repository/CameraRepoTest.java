package com.springboot.SpringBackend.repository;

import com.springboot.SpringBackend.model.Camera;
import com.springboot.SpringBackend.model.Network;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

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
        Network net = new Network("RaspberryPi4");
        netRepo.save(net);

        Camera cam = new Camera("SomeURL",net);
        camRepo.save(cam);

        assertNotNull(cam.getCameraId());
        Camera newCam = camRepo.findById(cam.getCameraId()).orElse(null);
        assert newCam != null;
        assertEquals("SomeURL", newCam.getServerURL());
        assertEquals("RaspberryPi4", newCam.getNetwork().getNetName());
    }
}

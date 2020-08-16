package com.springboot.SpringBackend.repoTest;

import com.springboot.SpringBackend.model.Image;
import com.springboot.SpringBackend.model.Vehicle;
import com.springboot.SpringBackend.repository.ImageRepo;
import com.springboot.SpringBackend.repository.VehicleRepo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class VehicleRepoTest {
    private static final String IMAGE_URL = "http://an-imageurl.com/image1.jpg";
    private static final String LICENCE = "36FW58GP";

    @Autowired
    private ImageRepo irepo;
    @Autowired
    private VehicleRepo vrepo;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testPersistence() {
        Image img = new Image(IMAGE_URL);
        irepo.save(img);

        Vehicle v = new Vehicle(img,LICENCE);
        vrepo.save(v);

        Assert.assertNotNull(v.getVehicleId());
        Vehicle newV = vrepo.findById(v.getVehicleId()).orElse(null);
        Assert.assertEquals((Long) 1L, newV.getVehicleId());
        Assert.assertEquals(LICENCE, newV.getLicenseNo());
        Assert.assertEquals("Grey", newV.getVehicleListed());
    }

}

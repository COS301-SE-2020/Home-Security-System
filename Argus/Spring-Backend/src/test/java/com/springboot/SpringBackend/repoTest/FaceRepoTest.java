package com.springboot.SpringBackend.repoTest;

import com.springboot.SpringBackend.model.Face;
import com.springboot.SpringBackend.model.Person;
import com.springboot.SpringBackend.repository.FaceRepo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class FaceRepoTest {
    private static final String IMAGE_URL = "http://an-imageurl.com/image1.jpg";

    @Autowired
    private FaceRepo repo;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testPersistence() {
        /*Person person = new Person();
        Face face = new Face();
        face.setPhoto(IMAGE_URL);

        repo.save(face);

        Assert.assertNotNull(face.getFaceId());
        Face newFace = repo.findById(face.getFaceId()).orElse(null);
        Assert.assertEquals((Long) 1L, newFace.getFaceId());
        Assert.assertEquals(IMAGE_URL, newFace.getFeatures());

         */
    }
}

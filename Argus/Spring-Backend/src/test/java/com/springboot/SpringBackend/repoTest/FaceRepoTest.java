/*
package com.springboot.SpringBackend.repoTest;

import com.springboot.SpringBackend.model.Face;
import com.springboot.SpringBackend.model.Image;
import com.springboot.SpringBackend.model.Person;
import com.springboot.SpringBackend.repository.FaceRepo;
import com.springboot.SpringBackend.repository.ImageRepo;
import com.springboot.SpringBackend.repository.PersonRepo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FaceRepoTest {
    private static final String IMAGE_URL = "http://an-imageurl.com/image1.jpg";
    private static final String FEATURES = "Face";

    // @Autowired
    // private ImageRepo irepo;
    @Autowired
    private PersonRepo prepo;
    @Autowired
    private FaceRepo frepo;

    @Before
    public void setUp() {

    }

    @Test
    public void testPersistence() {
        // Image img = new Image(IMAGE_URL);
        // irepo.save(img);

        Person person = new Person(IMAGE_URL);
        prepo.save(person);

        Face face = new Face(person, FEATURES);
        frepo.save(face);

        Assert.assertNotNull(face.getFaceId());
        Face newFace = frepo.findById(face.getFaceId()).orElse(null);
        Assert.assertEquals((Long) 1L, newFace.getFaceId());
        Assert.assertEquals(FEATURES, newFace.getFeatures());
    }
}
*/

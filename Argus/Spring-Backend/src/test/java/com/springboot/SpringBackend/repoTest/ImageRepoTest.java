package com.springboot.SpringBackend.repoTest;

import com.springboot.SpringBackend.model.Image;
import com.springboot.SpringBackend.model.Person;
import com.springboot.SpringBackend.repository.ImageRepo;
import com.springboot.SpringBackend.repository.PersonRepo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ImageRepoTest {
    private static final String IMAGE_URL = "http://an-imageurl.com/image1.jpg";

    @Autowired
    private ImageRepo repo;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testPersistence() {
        Image img = new Image();
        img.setPhoto(IMAGE_URL);

        repo.save(img);

        Assert.assertNotNull(img.getImageId());
        Image newImg = repo.findById(img.getImageId()).orElse(null);
        Assert.assertEquals((Long) 1L, newImg.getImageId());
        Assert.assertEquals(IMAGE_URL, newImg.getPhoto());
    }
}

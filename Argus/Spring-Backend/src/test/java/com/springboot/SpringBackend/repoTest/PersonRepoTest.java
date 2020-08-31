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
import org.springframework.test.context.junit4.SpringRunner;

//@RunWith(SpringJUnit4ClassRunner.class)
@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonRepoTest {
    private static final String IMAGE_URL = "http://an-imageurl.com/image1.jpg";

    // @Autowired
    // private ImageRepo irepo;
    @Autowired
    private PersonRepo prepo;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testPersistence() {
        // Image img = new Image(IMAGE_URL);
        // irepo.save(img);

        Person person = new Person(IMAGE_URL);
        prepo.save(person);

        Assert.assertNotNull(person.getPersonId());
        Person newPsn = prepo.findById(person.getPersonId()).orElse(null);
        Assert.assertEquals((Long) 1L, newPsn.getPersonId());
        Assert.assertEquals(IMAGE_URL, newPsn.getPersonImg());
        Assert.assertEquals("Unknown", newPsn.getFname());
        Assert.assertEquals("Unknown", newPsn.getLname());
        Assert.assertEquals("Grey", newPsn.getPersonListed());
    }
}

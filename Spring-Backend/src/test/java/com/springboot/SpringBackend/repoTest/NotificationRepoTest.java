/*
package com.springboot.SpringBackend.repoTest;

import com.springboot.SpringBackend.model.Image;
import com.springboot.SpringBackend.model.Notification;
import com.springboot.SpringBackend.model.Person;
import com.springboot.SpringBackend.model.User;
import com.springboot.SpringBackend.repository.NotificationRepo;
import com.springboot.SpringBackend.repository.UserRepo;
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
public class NotificationRepoTest {
    private static final String IMAGE_URL = "http://an-imageurl.com/image1.jpg";
    private static final String FNAME = "Brad";
    private static final String LNAME = "Zietsman";
    private static final String EMAIL = "brad.zietsman@gmail.com";
    private static final String USERNAME = "Bradford";
    private static final String PASS = "1234";
    private static final String ROLE = "Admin";
    private static final String MSG = "Hello World";
    private static final String LISTED = "Suspicious";
    private static final String CONTACT = "0840763231";

    // @Autowired
    // private ImageRepo irepo;
    @Autowired
    private UserRepo urepo;
    @Autowired
    private NotificationRepo nrepo;

    @Before
    public void setUp() {

    }

    @Test
    public void testPersistence() {
        // Image img = new Image(IMAGE_URL);
        // irepo.save(img);

        User user = new User(FNAME,LNAME, CONTACT,EMAIL,USERNAME,PASS,ROLE);
        urepo.save(user);

        Notification note = new Notification(IMAGE_URL,MSG,user);
        nrepo.save(note);

        Assert.assertNotNull(note.getNotificationId());
        Notification newNote = nrepo.findById(note.getNotificationId()).orElse(null);
        Assert.assertEquals((long) 1L, newNote.getNotificationId());
        Assert.assertEquals(IMAGE_URL, newNote.getNotificationImg());
        Assert.assertEquals(MSG, newNote.getMessage());
        Assert.assertEquals(LISTED, newNote.getListed());
    }
}
*/

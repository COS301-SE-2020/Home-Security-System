/*
package com.springboot.SpringBackend.repoTest;

import com.springboot.SpringBackend.model.User;
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
public class UserRepoTest {
    private static final String IMAGE_URL = "http://an-imageurl.com/image1.jpg";
    private static final String FNAME = "Brad";
    private static final String LNAME = "Zietsman";
    private static final String EMAIL = "brad.zietsman@gmail.com";
    private static final String USERNAME = "Bradford";
    private static final String PASS = "1234";
    private static final String ROLE = "Admin";

    @Autowired
    private UserRepo repo;

    @Before
    public void setUp() {

    }

    @Test
    public void testPersistence() {
        User user = new User(IMAGE_URL, FNAME,LNAME,EMAIL,USERNAME,PASS,ROLE);
        repo.save(user);

        Assert.assertNotNull(user.getUserId());
        User newUsr = repo.findById(user.getUserId()).orElse(null);
        Assert.assertEquals((long) 1L, newUsr.getUserId());
        Assert.assertEquals(IMAGE_URL, newUsr.getProfilePhoto());
        Assert.assertEquals(FNAME, newUsr.getFname());
        Assert.assertEquals(LNAME, newUsr.getLname());
        Assert.assertEquals(EMAIL, newUsr.getEmail());
        Assert.assertEquals(USERNAME, newUsr.getUsername());
        Assert.assertEquals(PASS, newUsr.getUserPass());
        Assert.assertEquals(ROLE, newUsr.getUserRole());
    }
}
*/

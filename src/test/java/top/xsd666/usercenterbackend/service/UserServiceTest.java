package top.xsd666.usercenterbackend.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.xsd666.usercenterbackend.model.User;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void insertUser() {
        User user = new User();
        user.setUsername("example");
        user.setGender(0);
        user.setAvatar("https://example.com");
        user.setPassword("password");
        user.setPhone("12345678900");
        user.setEmail("example@example.com");
        boolean res = userService.save(user);
        System.out.println(res);
        assertTrue(res);
    }


    @Test
    public void register() {
        long res = this.userService.register("18758579255", "examplePassword");
        assertEquals(1, res);
    }
}
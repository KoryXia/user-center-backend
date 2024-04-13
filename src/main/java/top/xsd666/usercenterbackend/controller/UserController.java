package top.xsd666.usercenterbackend.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.xsd666.usercenterbackend.model.User;
import top.xsd666.usercenterbackend.model.request.UserRequest;
import top.xsd666.usercenterbackend.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public Long register(@RequestBody UserRequest registerRequest) {
        if (registerRequest == null) {
            return null;
        }

        String phone = registerRequest.phone();
        String password = registerRequest.password();

        if (StringUtils.isAnyBlank(phone, password)) {
            return null;
        }

        return this.userService.register(phone, password);
    }

    @PostMapping("/login")
    public User login(@RequestBody UserRequest loginRequest, HttpServletRequest request) {
        if (loginRequest == null) {
            return null;
        }

        String phone = loginRequest.phone();
        String password = loginRequest.password();

        if (StringUtils.isAnyBlank(phone, password)) {
            return null;
        }

        return this.userService.login(loginRequest.phone(), loginRequest.password(), request);
    }
}

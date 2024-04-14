package top.xsd666.usercenterbackend.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.xsd666.usercenterbackend.model.User;
import top.xsd666.usercenterbackend.model.request.UserBasicRequest;
import top.xsd666.usercenterbackend.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public Long register(@RequestBody UserBasicRequest registerRequest) {
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
    public User login(@RequestBody UserBasicRequest loginRequest, HttpServletRequest request) {
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

    @GetMapping("/{phone}")
    public List<User> getUsers(@PathVariable String phone) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(phone)) {
            queryWrapper.eq("phone", phone);
        }
        return this.userService.list(queryWrapper);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteUser(@PathVariable long id) {
        if (id <= 0) {
            return false;
        }
        return this.userService.removeById(id);
    }
}

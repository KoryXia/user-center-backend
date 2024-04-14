package top.xsd666.usercenterbackend.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.xsd666.usercenterbackend.model.User;
import top.xsd666.usercenterbackend.model.request.UserBasicRequest;
import top.xsd666.usercenterbackend.service.UserService;

import java.util.ArrayList;
import java.util.List;

import static top.xsd666.usercenterbackend.constant.UserConstant.*;

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
    public List<User> getUsers(@PathVariable String phone, HttpServletRequest request) {
        if (!this.isAdmin(request)) {
            return new ArrayList<>();
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(phone)) {
            queryWrapper.eq("phone", phone);
        }
        List<User> users = this.userService.list(queryWrapper);
        users.forEach(this.userService::desensitize);
        return users;
    }

    @DeleteMapping("/{id}")
    public Boolean deleteUser(@PathVariable long id, HttpServletRequest request) {
        if (!this.isAdmin(request)) {
            return false;
        }
        if (id <= 0) {
            return false;
        }
        return this.userService.removeById(id);
    }


    private boolean isAdmin(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(USER_LOGIN_STATE);
        return user != null && user.getUserRole() == ADMIN_ROLE;
    }
}

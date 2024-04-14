package top.xsd666.usercenterbackend.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import top.xsd666.usercenterbackend.mapper.UserMapper;
import top.xsd666.usercenterbackend.model.User;

import static top.xsd666.usercenterbackend.constant.UserConstant.*;

@Service
@Slf4j
public class UserService extends ServiceImpl<UserMapper, User> {
    public static final String SALT = "xsd";

    public long register(String phone, String password) {
        if (StringUtils.isAnyBlank(phone, password)) {
            return 0;
        }

        if (!StringUtils.isNumeric(phone)) {
            return 0;
        }

        if (password.length() < 8) {
            return 0;
        }

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", phone);
        if (this.count(queryWrapper) > 0) {
            return 0;
        }

        String encryptedPassword = DigestUtils.md5DigestAsHex((SALT + password).getBytes());

        User newUser = new User();
        newUser.setPhone(phone);
        newUser.setPassword(encryptedPassword);
        newUser.setUsername(RandomStringUtils.randomAlphanumeric(9));

        if (!this.save(newUser)) {
            return 0;
        }

        return newUser.getId();
    }

    public User login(String phone, String password, HttpServletRequest request) {
        if (StringUtils.isAnyBlank(phone, password)) {
            return null;
        }

        if (!StringUtils.isNumeric(phone)) {
            return null;
        }

        if (password.length() < 8) {
            return null;
        }

        String encryptedPassword = DigestUtils.md5DigestAsHex((SALT + password).getBytes());
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", phone);
        queryWrapper.eq("password", encryptedPassword);
        User user = this.getOne(queryWrapper);
        if (user == null) {
            log.info("User login failed, user account can't match in database");
            return null;
        }

        user.setPassword(null);
        user.setIsDelete(null);
        user.setUpdateTime(null);
        request.getSession().setAttribute(USER_LOGIN_STATE, user);
        return user;
    }

    public void desensitize(User user) {
        user.setPassword(null);
        user.setIsDelete(null);
        user.setUpdateTime(null);
    }
}

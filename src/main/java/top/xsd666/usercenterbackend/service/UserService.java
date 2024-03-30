package top.xsd666.usercenterbackend.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import top.xsd666.usercenterbackend.mapper.UserMapper;
import top.xsd666.usercenterbackend.model.User;

import java.util.UUID;

/**
 * @author xia
 * @description 针对表【user】的数据库操作Service
 * @createDate 2024-03-30 16:31:57
 */
@Service
public class UserService extends ServiceImpl<UserMapper, User> {
    public long register(String phone, String password) {
//      1. Input check
        if (StringUtils.isAnyBlank(phone, password)) {
            return 0;
        }

        System.out.println(StringUtils.isNumeric(phone));
//        optimize phone check later
        if (!StringUtils.isNumeric(phone)) {
            return 0;
        }

        if (password.length() < 8) {
            return 0;
        }

//      2. Duplication check
        QueryWrapper<User> queryWrapper= new QueryWrapper<>();
        queryWrapper.eq("phone", phone);
        if (this.count(queryWrapper) > 0) {
            return 0;
        }

//      3. Encrypt password
        String encryptedPassword = DigestUtils.md5DigestAsHex(("xsd" + password).getBytes());

//      4. Insert into table
        User newUser = new User();
        newUser.setPhone(phone);
        newUser.setPassword(encryptedPassword);
        newUser.setUsername(RandomStringUtils.randomAlphanumeric(9));

        if (!this.save(newUser)) {
            return 0;
        }

        return 1;
    }
}

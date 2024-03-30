package top.xsd666.usercenterbackend.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.xsd666.usercenterbackend.mapper.UserMapper;
import top.xsd666.usercenterbackend.model.User;

/**
 * @author xia
 * @description 针对表【user】的数据库操作Service
 * @createDate 2024-03-30 16:31:57
 */
@Service
public class UserService extends ServiceImpl<UserMapper, User> {

}

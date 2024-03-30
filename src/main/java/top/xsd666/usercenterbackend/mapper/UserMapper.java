package top.xsd666.usercenterbackend.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.xsd666.usercenterbackend.model.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author xia
* @description 针对表【user】的数据库操作Mapper
* @createDate 2024-03-30 16:05:26
* @Entity top.xsd666.usercenterbackend.model.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}





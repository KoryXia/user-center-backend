package top.xsd666.usercenterbackend.model;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;


@TableName(value ="user")
@Data
public class User implements Serializable {
    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * Username
     */
    private String username;

    /**
     * Gender
     */
    private Integer gender;

    /**
     * Avatar
     */
    private String avatar;

    /**
     * Password
     */
    private String password;

    /**
     * Phone number
     */
    private String phone;

    /**
     * Email address
     */
    private String email;

    /**
     * User status
     */
    private Integer status;

    /**
     * Create Time
     */
    @TableField("createTime")
    private Date createTime;

    /**
     * Update Time
     */
    @TableField("updateTime")
    private Date updateTime;

    /**
     * Delete status
     */
    @TableLogic
    @TableField("isDelete")
    private Integer isDelete;

    /**
     * 0: Normal
     * 1: Admin
     */
    @TableField("userRole")
    private Integer userRole;


    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
package top.xsd666.usercenterbackend.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName user
 */
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
    private Date createTime;

    /**
     * Update Time
     */
    private Date updateTime;

    /**
     * Delete status
     */
    private Integer isDelete;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
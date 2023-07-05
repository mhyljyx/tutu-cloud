package com.tztang.authservice.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("sys_user")
public class SysUserEntity {

    @TableId(type = IdType.INPUT)
    private Integer id;

    private String userName;

    private String nickName;

    private String password;

    private String status;

    private String email;

    private String phoneNumber;

    private String sex;

    private String avatar;

    private String userType;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    @TableLogic
    private String isDel;

}

package com.tztang.authservice.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("sys_user_role")
public class SysUserRoleEntity {

    private Integer userId;

    private Integer roleId;

}

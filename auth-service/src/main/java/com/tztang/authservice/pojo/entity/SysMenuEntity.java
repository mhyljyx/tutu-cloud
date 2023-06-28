package com.tztang.authservice.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("sys_menu")
public class SysMenuEntity {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String menuName;

    private String path;

    private String component;

    private String visible;

    private String status;

    private String perms;

    private String icon;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private String remark;

    @TableLogic
    private String is_del;

}

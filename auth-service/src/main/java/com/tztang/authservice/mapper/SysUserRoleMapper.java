package com.tztang.authservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tztang.authservice.pojo.entity.SysUserEntity;
import com.tztang.authservice.pojo.entity.SysUserRoleEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysUserRoleMapper extends BaseMapper<SysUserRoleEntity> {

    String getRole(Integer userId);

}

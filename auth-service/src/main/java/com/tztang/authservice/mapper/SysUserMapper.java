package com.tztang.authservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tztang.authservice.pojo.entity.SysUserEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysUserMapper extends BaseMapper<SysUserEntity> {
}

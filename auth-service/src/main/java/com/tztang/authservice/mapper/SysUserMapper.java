package com.tztang.authservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tztang.authservice.pojo.entity.SysUserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysUserMapper extends BaseMapper<SysUserEntity> {

    List<String> getAuthority(Integer userId);

}

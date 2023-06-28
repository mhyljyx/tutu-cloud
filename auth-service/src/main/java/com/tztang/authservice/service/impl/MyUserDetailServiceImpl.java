package com.tztang.authservice.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.tztang.authservice.mapper.SysUserMapper;
import com.tztang.authservice.pojo.entity.LoginUser;
import com.tztang.authservice.pojo.entity.SysUserEntity;
import com.tztang.authservice.service.MyUserDetailService;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MyUserDetailServiceImpl implements MyUserDetailService {

    @Resource
    private SysUserMapper sysUserMapper;

    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        SysUserEntity sysUser = sysUserMapper.selectOne(
                Wrappers.<SysUserEntity>lambdaQuery()
                        .eq(SysUserEntity::getUserName, account)
        );
        if (ObjectUtil.isNull(sysUser)) {
            throw new UsernameNotFoundException("用户不存在");
        }
        /**
         * 权限
         */
        return new LoginUser(sysUser, AuthorityUtils.NO_AUTHORITIES);
    }

}

package com.tztang.authservice.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import cn.hutool.crypto.digest.MD5;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.tztang.authservice.mapper.SysUserMapper;
import com.tztang.authservice.pojo.entity.LoginUser;
import com.tztang.authservice.pojo.entity.SysUserEntity;
import com.tztang.authservice.service.MyUserCacheService;
import com.tztang.common.util.JwtUtil;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.HashMap;

@Validated
@RestController
public class TestController {

    @Resource
    private MyUserCacheService myUserCacheService;
    @Resource
    private SysUserMapper sysUserMapper;

    @GetMapping("/test")
    public String test() {
        return "hello world";
    }

    @GetMapping("/hello")
    public String hello() {
        return "我是彩笔";
    }

    @PostMapping("/login")
    public String login(@RequestParam String userName, @RequestParam String password) {
        SysUserEntity sysUserEntity = sysUserMapper.selectOne(
                Wrappers.<SysUserEntity>lambdaQuery()
                        .eq(SysUserEntity::getUserName, userName)
        );
        if (!password.equals(sysUserEntity.getPassword())) {
            return "密码错误";
        }
        myUserCacheService.putUserInCache(new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
            }

            @Override
            public String getPassword() {
                return sysUserEntity.getPassword();
            }

            @Override
            public String getUsername() {
                return sysUserEntity.getUserName();
            }

            @Override
            public boolean isAccountNonExpired() {
                return true;
            }

            @Override
            public boolean isAccountNonLocked() {
                return true;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return true;
            }

            @Override
            public boolean isEnabled() {
                return true;
            }
        });
        HashMap<String, Object> map = new HashMap<>();
        map.put(JwtUtil.USER_NAME, userName);
        return JwtUtil.createJwt(map);
    }

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encode = encoder.encode("123456");
        System.out.println(encode);
    }

}

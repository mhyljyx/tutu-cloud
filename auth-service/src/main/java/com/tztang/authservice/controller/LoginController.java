package com.tztang.authservice.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import cn.hutool.crypto.digest.MD5;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.tztang.authservice.mapper.SysUserMapper;
import com.tztang.authservice.pojo.entity.LoginUser;
import com.tztang.authservice.pojo.entity.SysUserEntity;
import com.tztang.authservice.pojo.params.LoginParams;
import com.tztang.authservice.service.MyUserCacheService;
import com.tztang.authservice.service.MyUserDetailService;
import com.tztang.common.util.JwtUtil;
import com.tztang.common.util.ResultResponse;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.HashMap;

@Validated
@RestController
@RequestMapping("/security/")
public class LoginController {

    @Resource
    private MyUserDetailService myUserDetailService;

    @PostMapping("login")
    public ResultResponse login(@RequestBody LoginParams params) {
        return myUserDetailService.login(params);
    }

}

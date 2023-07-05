package com.tztang.authservice.service.impl;

import com.alibaba.fastjson2.JSON;
import com.tztang.authservice.pojo.entity.LoginUser;
import com.tztang.authservice.service.MyUserCacheService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MyUserCacheServiceImpl implements MyUserCacheService {

    @Resource(name = "stringRedisTemplateAuth_db")
    private StringRedisTemplate stringRedisTemplateAuthDb;

    @Override
    public LoginUser getUserFromCache(String username) {
        return JSON.parseObject(stringRedisTemplateAuthDb.opsForValue().get(username), LoginUser.class);
    }

    @Override
    public void putUserInCache(UserDetails user) {

    }

    @Override
    public void removeUserFromCache(String username) {

    }

}

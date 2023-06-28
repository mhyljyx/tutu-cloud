package com.tztang.authservice.service.impl;

import com.alibaba.fastjson2.JSON;
import com.tztang.authservice.service.MyUserCacheService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.cache.SpringCacheBasedUserCache;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MyUserCacheServiceImpl implements MyUserCacheService {

    @Resource(name = "stringRedisTemplateAuth_db")
    private StringRedisTemplate stringRedisTemplateAuthDb;

    public UserDetails getUserFromCache(String username) {
        return JSON.parseObject(stringRedisTemplateAuthDb.opsForValue().get(username), UserDetails.class);
    }

    public void putUserInCache(UserDetails user) {
        stringRedisTemplateAuthDb.opsForValue().set(user.getUsername(), JSON.toJSONString(user));
    }

    public void removeUserFromCache(String username) {
        stringRedisTemplateAuthDb.delete(username);
    }

}

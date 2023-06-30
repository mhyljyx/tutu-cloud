package com.tztang.authservice.service;

import com.tztang.authservice.pojo.entity.LoginUser;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.UserDetails;

public interface MyUserCacheService extends UserCache {

    @Override
    LoginUser getUserFromCache(String username);

    @Override
    void putUserInCache(UserDetails user);

    @Override
    void removeUserFromCache(String username);

}

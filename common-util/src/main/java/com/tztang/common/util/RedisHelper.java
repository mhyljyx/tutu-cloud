package com.tztang.common.util;

import com.tztang.common.contanst.RedisKeyConst;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

@Component
public class RedisHelper {

    @Resource(name = "stringRedisTemplateAuth_db")
    private StringRedisTemplate stringRedisTemplateAuthDb;

    /**
     * 向redis存储登录信息
     * @param key
     * @param map
     */
    public void storeLoginInfo(String key, Map<String, Object> map) {
        stringRedisTemplateAuthDb.opsForHash().putAll(key, map);
    }

    public Object getUserToken(String userName) {
        return stringRedisTemplateAuthDb.opsForHash().get(RedisKeyConst.LOGIN_KEY_PREFIX + userName, RedisKeyConst.TOKEN);
    }

    public Object getUserRole(String userName) {
        return stringRedisTemplateAuthDb.opsForHash().get(RedisKeyConst.LOGIN_KEY_PREFIX + userName, RedisKeyConst.USER_ROLE_KEY);
    }

}

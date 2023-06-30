package com.tztang.common.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

public class JwtUtil {

    private final static byte[] SING_KEY = "tutuCloud".getBytes(StandardCharsets.UTF_8);

    //用户名
    public static String USER_NAME = "userName";

    /**
     * 创建jwt
     * @param map
     * @return jwt
     */
    public static String createJwt(Map<String, Object> map) {
        return Jwts.builder()
                .setIssuedAt(new Date())
                .addClaims(map)
                .signWith(SignatureAlgorithm.HS256, SING_KEY)
                .setExpiration(new Date(System.currentTimeMillis() + 30000))
                .compact();
    }

    /**
     * 获取jwt的Claims
     * @param jwt
     * @return
     */
    private static Claims getClaims(String jwt) {
        return Jwts.parser()
                .setSigningKey(SING_KEY)
                .parseClaimsJws(jwt).getBody();
    }

    /**
     * 获取载荷中的数据key对应的value
     * @param key 载荷中的数据key
     * @return 载荷中的数据key对应的value
     */
    public static String getClaimsValue(String jwt, String key) {
        return (String) getClaims(jwt).get(key);
    }

}

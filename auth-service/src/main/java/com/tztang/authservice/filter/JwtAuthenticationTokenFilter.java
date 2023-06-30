package com.tztang.authservice.filter;

import cn.hutool.core.util.ObjectUtil;
import com.tztang.authservice.pojo.entity.LoginUser;
import com.tztang.authservice.service.MyUserCacheService;
import com.tztang.common.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Resource
    private MyUserCacheService myUserCacheService;

    // OncePerRequestFilter保证在一次请求只通过一次filter，而不需要重复执行
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getRequestURI().contains("/login")) {
            filterChain.doFilter(request, response);
            return;
        }
        //获取token
        String token = request.getHeader("token");
        // 解析token
        // 使用userName去redis中获取对应的LoginUser对象。
        String userName;
        try {
            userName = JwtUtil.getClaimsValue(token, JwtUtil.USER_NAME);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("token非法");
        }
        //从redis中获取用户信息
        String redisKey = "Login:" + userName;
        LoginUser loginUser = myUserCacheService.getUserFromCache(redisKey);
        if(ObjectUtil.isNull(loginUser)) {
            throw new RuntimeException("用户未登录");
        }
        //封装Authentication对象存入SecurityContextHolder
        //TODO 获取权限信息封装到Authentication中
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginUser,null,null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        //放行
        filterChain.doFilter(request, response);
    }

}

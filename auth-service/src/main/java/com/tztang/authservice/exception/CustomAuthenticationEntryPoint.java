package com.tztang.authservice.exception;

import com.alibaba.fastjson2.JSON;
import org.apache.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException {
        if (!response.isCommitted()) {
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
            response.setHeader("Content-Type", "application/json;charset=UTF-8");
            try (ServletOutputStream ous = response.getOutputStream()) {
                Map<String, Object> map = new HashMap<>();
                map.put("code", HttpStatus.SC_UNAUTHORIZED);
                map.put("success", false);
                map.put("message", Optional.ofNullable(request.getAttribute("errorMessage")).toString());
                ous.write(JSON.toJSONString(map).getBytes(StandardCharsets.UTF_8));
            }
        }
    }

}

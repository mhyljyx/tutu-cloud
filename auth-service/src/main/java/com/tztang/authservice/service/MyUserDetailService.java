package com.tztang.authservice.service;

import com.tztang.authservice.pojo.params.LoginParams;
import com.tztang.common.util.ResultResponse;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;

public interface MyUserDetailService extends UserDetailsService {

    ResultResponse login(LoginParams params);

}

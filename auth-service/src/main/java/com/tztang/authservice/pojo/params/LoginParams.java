package com.tztang.authservice.pojo.params;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

@Data
public class LoginParams {

    private String userName;

    private String password;

}

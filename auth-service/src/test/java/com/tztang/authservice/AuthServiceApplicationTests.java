package com.tztang.authservice;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Slf4j
@SpringBootTest
class AuthServiceApplicationTests {

    @Test
    void contextLoads() {
        log.info(new BCryptPasswordEncoder().encode("123456"));
    }

}

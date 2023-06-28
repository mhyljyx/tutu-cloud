package com.tutu.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class AdminManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminManagementApplication.class, args);
    }

}

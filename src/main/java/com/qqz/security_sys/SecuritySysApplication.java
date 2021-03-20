package com.qqz.security_sys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.qqz"})
@SpringBootApplication
public class SecuritySysApplication {

    public static void main(String[] args) {
        SpringApplication.run ( SecuritySysApplication.class , args );
    }

}

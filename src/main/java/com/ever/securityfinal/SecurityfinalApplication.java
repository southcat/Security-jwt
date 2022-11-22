package com.ever.securityfinal;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@MapperScan("com.ever.securityfinal.mapper")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityfinalApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityfinalApplication.class, args);
    }

}

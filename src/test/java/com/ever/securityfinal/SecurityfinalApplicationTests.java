package com.ever.securityfinal;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ever.securityfinal.entity.User;
import com.ever.securityfinal.mapper.UserMapper;
import com.ever.securityfinal.service.LoginService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootTest
class SecurityfinalApplicationTests {

    @Autowired
    UserMapper userMapper;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    LoginService loginService;
    @Autowired
    UserDetailsService userDetailsService;


    @Test
    void contextLoads() {
        String username = "admin";
//        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.eq(User::getUsername,username);
//        User user = userMapper.loadUserByUsername(username);
//        System.out.println(user);
//        User user = new User();
//        user.setUsername("admin");
//        user.setPassword("123456");
//        System.out.println(loginService.login(user));
        UserDetails user  = userDetailsService.loadUserByUsername("admin");
        System.out.println(user);


    }

}

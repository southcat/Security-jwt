package com.ever.securityfinal.controller;

import com.ever.securityfinal.entity.User;
import com.ever.securityfinal.service.LoginService;
import com.ever.securityfinal.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    LoginService loginService;

    @PostMapping("login")
    public ResponseResult login(@RequestBody User user) {

        return loginService.login(user);
    }

    //    @GetMapping("test")
//    @PreAuthorize("hasAuthority('sayhello')")
//    public String test(){
//        return "sayhello权限通过";
//    }
//    @GetMapping("test2")
////    @PreAuthorize("hasAuthority('sayhello1')")
//    public String test2(){
//        return "sayhello1权限通过";
//    }
    @GetMapping("/user/hello")
    public String userHello() {
        return "你好，普通用户！";
    }

    @GetMapping("/admin/hello")
    public String adminHello() {
        return "你好，管理员！";
    }

    @GetMapping("/db/hello")
    public String dbaHello() {
        return "你好，数据库管理员！";
    }
    @GetMapping("/test")
    public String test() {
        return "test";
    }


    @PostMapping("logout")
    public ResponseResult logout() {
        return loginService.logout();

    }
}

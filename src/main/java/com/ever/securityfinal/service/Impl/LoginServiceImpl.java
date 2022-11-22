package com.ever.securityfinal.service.Impl;

import com.ever.securityfinal.entity.User;
import com.ever.securityfinal.mapper.UserMapper;
import com.ever.securityfinal.service.LoginService;
import com.ever.securityfinal.utils.JwtUtil;
import com.ever.securityfinal.utils.RedisCache;
import com.ever.securityfinal.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    RedisCache redisCache;

    @Override
    public ResponseResult login(User user) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword());
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        if (Objects.isNull(authentication)){
            throw new RuntimeException("用户名或密码错误");
        }

        User loginUser = (User) (authentication.getPrincipal());
        String userId = loginUser.getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        Map<String,String> map = new HashMap<>();
        map.put("token",jwt);
        redisCache.setCacheObject("login1"+userId,loginUser);
        return new ResponseResult<>(200,"login success",map);
    }

    @Override
    public ResponseResult logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User loginUser = (User) authentication.getPrincipal();
        Integer userId = loginUser.getId();
        redisCache.deleteObject("login"+userId);
        return new ResponseResult<>(200,"登陆成功");
    }
}

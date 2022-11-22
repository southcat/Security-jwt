package com.ever.securityfinal.service;

import com.ever.securityfinal.entity.User;
import com.ever.securityfinal.utils.ResponseResult;

public interface LoginService {
    ResponseResult login(User user);

    ResponseResult logout();
}

package com.ever.securityfinal.exception;

import com.alibaba.fastjson.JSON;
import com.ever.securityfinal.utils.ResponseResult;
import com.ever.securityfinal.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 没有权限
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        //给前端ResponseResult 的json
        ResponseResult responseResult = new ResponseResult(HttpStatus.FORBIDDEN.value(), "您权限不足！");
        String json = JSON.toJSONString(responseResult);
        WebUtils.renderString(response,json);
    }
}


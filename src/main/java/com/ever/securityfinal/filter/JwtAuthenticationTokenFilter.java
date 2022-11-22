package com.ever.securityfinal.filter;

import com.alibaba.fastjson.JSON;
import com.ever.securityfinal.entity.User;
import com.ever.securityfinal.utils.JwtUtil;
import com.ever.securityfinal.utils.RedisCache;
import com.ever.securityfinal.utils.ResponseResult;
import com.ever.securityfinal.utils.WebUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("token");
        if (!StringUtils.hasText(token)){
            filterChain.doFilter(request,response);
            return;
        }
        String userId;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userId = claims.getSubject();
        } catch (Exception e) {
            ResponseResult responseResult = new ResponseResult(HttpStatus.UNAUTHORIZED.value(), "非法Token，请重新登陆！");
            String json = JSON.toJSONString(responseResult);
            WebUtils.renderString(response,json);
            return;
        }

//        LoginUser loginUser =redisCache.getCacheObject("login"+userId);
        User user = redisCache.getCacheObject("login1"+userId);
        if (Objects.isNull(user)){
            ResponseResult responseResult = new ResponseResult(HttpStatus.UNAUTHORIZED.value(), "用户尚未登陆，请先登陆！");
            String json = JSON.toJSONString(responseResult);
            WebUtils.renderString(response,json);
            return;
        }

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user,user.getPassword(),user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        filterChain.doFilter(request,response);

    }
}

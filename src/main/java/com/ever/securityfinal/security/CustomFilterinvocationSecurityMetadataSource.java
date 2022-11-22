package com.ever.securityfinal.security;

import com.ever.securityfinal.entity.Menu;
import com.ever.securityfinal.entity.Role;
import com.ever.securityfinal.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

public class CustomFilterinvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    AntPathMatcher antPathMatcher = new AntPathMatcher();
    @Autowired
    MenuMapper menuMapper;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        String requesturl = ((FilterInvocation) object).getRequestUrl();
        if (requesturl.equals("/login")) {
            return null;
        }
        List<Menu> menuList = menuMapper.getAllMenus();
        for (Menu menu : menuList) {
            if (antPathMatcher.match(menu.getPattern(), requesturl)) {
                List<Role> roleList = menu.getRoles();
                String[] roleArr = new String[roleList.size()];
                for (int i = 0; i < roleArr.length; i++) {
                    roleArr[i] = roleList.get(i).getRolename();
                }
                return SecurityConfig.createList(roleArr);
            }
        }
        return SecurityConfig.createList("ROLE_ANONYMOUS");

    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}

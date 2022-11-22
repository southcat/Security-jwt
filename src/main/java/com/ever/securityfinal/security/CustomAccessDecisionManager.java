package com.ever.securityfinal.security;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class CustomAccessDecisionManager implements AccessDecisionManager{
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        Collection<? extends GrantedAuthority> auths = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        for (ConfigAttribute configAttribute:configAttributes){
            if ("ROLE_ANONYMOUS".equals(configAttribute.getAttribute())&& authentication instanceof UsernamePasswordAuthenticationToken){
                return;
            }
            for (GrantedAuthority grantedAuthority:auths){
                if (configAttribute.getAttribute().equals(grantedAuthority.getAuthority())){
                    return;
                }
            }
            throw new AccessDeniedException("权限不足");

        }
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }
}

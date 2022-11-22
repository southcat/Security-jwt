package com.ever.securityfinal.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "user")

public class User implements UserDetails,Serializable {
    private static final long serialVersionUID = 1L;
    @TableId
    private Integer id;
    private  String username;
    private  String password;
    private Boolean enabled;
    private Boolean locked;
    private List<Role> roles;

    /**
     * 获取当前用户对象所具有的角色信息
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>() ;
        for (Role role : roles){
            authorities.add (new SimpleGrantedAuthority (role.getRolename()) ) ;
        }
        return authorities;
    }

    /**
     * 获取当前用户的密码
     * @return
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * 获取d当前用户的用户名
     * @return
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     * 当前账户是否未过期
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 当前账户是否未锁定
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    /**
     * 当前账户密码是否未过期
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 当前账户是否可用
     * @return
     */
    @Override
    public boolean isEnabled() {
        return enabled;
    }

    //省略getter、setter
}
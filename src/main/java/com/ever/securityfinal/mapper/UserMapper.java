package com.ever.securityfinal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ever.securityfinal.entity.Role;
import com.ever.securityfinal.entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {
    List<Role> selectRolesByUserId(Integer userId);
    @Select("select * from user where username=#{username}")
    User loadUserByUsername(String username);
}
package com.ever.securityfinal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ever.securityfinal.entity.Menu;

import java.util.List;

public interface MenuMapper extends BaseMapper<Menu> {
    List<String> selectPermsByUser(Long userId);
    List<Menu> getAllMenus();
}

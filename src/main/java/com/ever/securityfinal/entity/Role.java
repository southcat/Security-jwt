package com.ever.securityfinal.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName(value = "role")
@Data
public class Role {
    private  Integer id;
    private  String rolename;
    private  String note;
}

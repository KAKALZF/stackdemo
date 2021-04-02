package com.ample16.stackdemo.mapper;

import com.ample16.stackdemo.pojo.dto.RolePermDo;

public interface RolePermMapper {
    int insert(RolePermDo record);

    int insertSelective(RolePermDo record);
}
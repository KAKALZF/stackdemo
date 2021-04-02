package com.ample16.stackdemo.mapper;

import com.ample16.stackdemo.pojo.dto.PermissionDo;

public interface PermissionMapper {
    int insert(PermissionDo record);

    int insertSelective(PermissionDo record);
}
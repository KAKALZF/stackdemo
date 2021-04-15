package com.ample16.stackdemo.mapper;

import com.ample16.stackdemo.pojo.dto.PermissionDo;

public interface PermissionMapper {

    int insertSelective(PermissionDo record);

    void update(PermissionDo permissionDo);
}

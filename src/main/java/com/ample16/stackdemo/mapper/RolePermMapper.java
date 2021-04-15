package com.ample16.stackdemo.mapper;

import org.apache.ibatis.annotations.Param;

import com.ample16.stackdemo.pojo.dto.RolePermDo;

import java.util.List;

public interface RolePermMapper {
    int insert(RolePermDo record);

    int insertSelective(RolePermDo record);

    int deleteByRoleId(@Param("roleId") Long roleId);

    int batchInsert(@Param("rolePermDos") List<RolePermDo> rolePermDos);

}

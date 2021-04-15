package com.ample16.stackdemo.mapper;

import org.apache.ibatis.annotations.Param;

import com.ample16.stackdemo.pojo.dto.RolePermDo;

import java.util.List;

public interface RolePermMapper {
    int insert(RolePermDo record);

    int insertSelective(RolePermDo record);

    int deleteByRoleId(@Param("roleId") Long roleId);

    int batchInsert(@Param("rolePermDos") List<RolePermDo> rolePermDos);

    List<RolePermDo> findAllByRoleId(@Param("roleId") Long roleId);

    List<RolePermDo> findAllByRoleIds(@Param("roleIds") List<Long> roleIds);


}

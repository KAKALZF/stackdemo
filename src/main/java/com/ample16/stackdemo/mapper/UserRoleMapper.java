package com.ample16.stackdemo.mapper;

import org.apache.ibatis.annotations.Param;

import com.ample16.stackdemo.pojo.dto.UserRoleDo;

import java.util.List;

public interface UserRoleMapper {

    int insertSelective(UserRoleDo record);

    int deleteByUserId(@Param("userId") Long userId);

    int batchInsert(@Param("userRoles") List<UserRoleDo> userRoles);


}

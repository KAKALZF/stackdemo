package com.ample16.stackdemo.mapper;

import com.ample16.stackdemo.pojo.dto.RoleDo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RoleMapper {

    int add(RoleDo record);

    RoleDo findAllById(@Param("id") Long id);

    int update(RoleDo roleDo);

//    deleteByUserId(@Param("userId") Long userId);
}

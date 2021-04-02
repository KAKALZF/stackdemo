package com.ample16.stackdemo.mapper;

import com.ample16.stackdemo.pojo.dto.RoleDo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RoleMapper {
    int insert(RoleDo record);

    int insertSelective(RoleDo record);

    RoleDo findAllById(@Param("id") Long id);


//    deleteByUserId(@Param("userId") Long userId);
}

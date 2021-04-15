package com.ample16.stackdemo.mapper;

import com.ample16.stackdemo.pojo.dto.RoleDo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleMapper {

    int add(RoleDo record);

    RoleDo findById(@Param("id") Long id);

    int update(RoleDo roleDo);

    List<RoleDo> findByIds(@Param("roleIds") List<Long> roleIds);


}

package com.ample16.stackdemo.mapper;

import org.apache.ibatis.annotations.Param;

import com.ample16.stackdemo.pojo.dto.PermissionDo;

import java.util.List;

public interface PermissionMapper {

    int insertSelective(PermissionDo record);

    void update(PermissionDo permissionDo);

    PermissionDo findById(@Param("id") Long id);

    List<PermissionDo> findByIds(@Param("ids") List<Long> ids);


}

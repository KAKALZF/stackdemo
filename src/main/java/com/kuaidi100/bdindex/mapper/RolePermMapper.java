package com.kuaidi100.bdindex.mapper;

import com.kuaidi100.bdindex.pojo.dto.RolePermDo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RolePermMapper {
    int insert(RolePermDo record);

    int insertSelective(RolePermDo record);

    int deleteByRoleId(@Param("roleId") Long roleId);

    int batchInsert(@Param("rolePermDos") List<RolePermDo> rolePermDos);

    List<RolePermDo> findAllByRoleId(@Param("roleId") Long roleId);

    List<RolePermDo> findAllByRoleIds(@Param("roleIds") List<Long> roleIds);


}

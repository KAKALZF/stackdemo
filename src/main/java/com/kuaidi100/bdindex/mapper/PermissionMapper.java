package com.kuaidi100.bdindex.mapper;

import com.kuaidi100.bdindex.pojo.dto.PermissionDo;
import com.kuaidi100.bdindex.pojo.req.PermissionsQueryReq;
import org.apache.ibatis.annotations.Param;

import java.security.Permission;
import java.util.List;

public interface PermissionMapper {

    int insertSelective(PermissionDo record);

    void update(PermissionDo permissionDo);

    PermissionDo findById(@Param("id") Long id);

    List<PermissionDo> findByIds(@Param("ids") List<Long> ids);

    PermissionDo findByName(@Param("name") String name);

    List<Long> findAllId();

    Integer deleteByIds(@Param("ids") List<Long> ids);

    int deleteById(@Param("id") Long id);

    List<PermissionDo> queryList(PermissionsQueryReq permissionsQueryReq);


}

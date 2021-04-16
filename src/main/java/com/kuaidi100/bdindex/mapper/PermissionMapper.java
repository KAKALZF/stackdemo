package com.kuaidi100.bdindex.mapper;

import com.kuaidi100.bdindex.pojo.dto.PermissionDo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PermissionMapper {

    int insertSelective(PermissionDo record);

    void update(PermissionDo permissionDo);

    PermissionDo findById(@Param("id") Long id);

    List<PermissionDo> findByIds(@Param("ids") List<Long> ids);


}

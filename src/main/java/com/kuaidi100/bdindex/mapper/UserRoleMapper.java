package com.kuaidi100.bdindex.mapper;

import com.kuaidi100.bdindex.pojo.dto.UserRoleDo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserRoleMapper {

    int insertSelective(UserRoleDo record);

    int deleteByUserId(@Param("userId") Long userId);

    int batchInsert(@Param("userRoles") List<UserRoleDo> userRoles);

    List<UserRoleDo> findAllByUserId(@Param("userId")Long userId);



}

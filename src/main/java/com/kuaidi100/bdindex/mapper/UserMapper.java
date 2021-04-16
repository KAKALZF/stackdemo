package com.kuaidi100.bdindex.mapper;

import com.kuaidi100.bdindex.pojo.dto.UserDo;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    Long add(UserDo record);

    int update(UserDo record);

    int deleteById(Long userId);

    UserDo findById(@Param("userId") Long userId);

    UserDo findByClientId(@Param("clientId") Long clientId);



}

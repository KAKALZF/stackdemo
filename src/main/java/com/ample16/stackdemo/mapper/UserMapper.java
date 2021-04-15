package com.ample16.stackdemo.mapper;

import java.util.List;

import com.ample16.stackdemo.pojo.dto.UserDo;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    Long add(UserDo record);

    int update(UserDo record);

    int deleteById(Long userId);

    UserDo findById(@Param("userId") Long userId);

    UserDo findByClientId(@Param("clientId") Long clientId);



}

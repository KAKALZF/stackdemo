package com.ample16.stackdemo.mapper;

import com.ample16.stackdemo.pojo.dto.UserDo;

public interface UserMapper {

    Long add(UserDo record);

    int update(UserDo record);

}

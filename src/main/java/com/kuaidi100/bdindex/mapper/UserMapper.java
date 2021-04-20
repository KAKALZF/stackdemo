package com.kuaidi100.bdindex.mapper;

import com.kuaidi100.bdindex.pojo.dto.UserDo;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    Long add(UserDo record);

    int update(UserDo record);

    int deleteByClientId(Long clientId);

    UserDo findByClientId(@Param("clientId") Long clientId);

}

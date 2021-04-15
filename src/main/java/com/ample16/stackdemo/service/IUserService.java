package com.ample16.stackdemo.service;

import com.ample16.stackdemo.pojo.dto.UserDo;
import com.ample16.stackdemo.pojo.req.UserAddOrUpdateReq;
import com.ample16.stackdemo.pojo.resp.UserInfoResp;

/**
 * @author zefeng_lin
 * @date 2021-04-01 14:41
 * @description
 */
public interface IUserService {

    void addOrUpdate(UserAddOrUpdateReq userAddOrUpdateReq);


    void delete(Long userId);

    UserDo findByUserId(Long userId);


    UserInfoResp getUserInfo(Long clientId);
}

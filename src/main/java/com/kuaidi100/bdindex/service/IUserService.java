package com.kuaidi100.bdindex.service;

import com.kuaidi100.bdindex.pojo.dto.UserDo;
import com.kuaidi100.bdindex.pojo.req.UserAddOrUpdateReq;
import com.kuaidi100.bdindex.pojo.resp.UserInfoVo;

/**
 * @author zefeng_lin
 * @date 2021-04-01 14:41
 * @description
 */
public interface IUserService {

    void add(UserAddOrUpdateReq userAddOrUpdateReq);

    void update(UserAddOrUpdateReq userAddOrUpdateReq);

    void delete(Long userId);

    UserInfoVo getUserInfo(Long clientId);

    void createAdminUser();

    UserDo findByClientId(Long clientId);
}

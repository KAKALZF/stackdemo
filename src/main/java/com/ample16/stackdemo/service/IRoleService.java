package com.ample16.stackdemo.service;

import com.ample16.stackdemo.pojo.dto.RoleDo;
import com.ample16.stackdemo.pojo.req.RoleAddOrUpdateReq;

/**
 * @author zefeng_lin
 * @date 2021-04-01 14:42
 * @description
 */
public interface IRoleService {
    void addOrUpdate(RoleAddOrUpdateReq roleAddOrUpdateReq);

    void delete(Long roleId);

    void findByRoleId(Long roleId);
}

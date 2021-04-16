package com.kuaidi100.bdindex.service;

import com.kuaidi100.bdindex.pojo.req.RoleAddOrUpdateReq;

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

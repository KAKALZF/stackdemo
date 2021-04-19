package com.kuaidi100.bdindex.service;

import com.kuaidi100.bdindex.pojo.dto.PermissionDo;
import com.kuaidi100.bdindex.pojo.req.PermissionAddOrUpdateReq;

/**
 * @author zefeng_lin
 * @date 2021-04-01 14:42
 * @description
 */
public interface IPermissionService {

    void addOrUpdate(PermissionAddOrUpdateReq permissionAddOrUpdateReq);

    void delete(Long permissionId);

    PermissionDo findByPermissionId(Long permissionId);

    void loadAllPermissions();
}

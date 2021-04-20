package com.kuaidi100.bdindex.service;

import com.github.pagehelper.PageInfo;
import com.kuaidi100.bdindex.pojo.dto.PermissionDo;
import com.kuaidi100.bdindex.pojo.req.PermissionAddOrUpdateReq;
import com.kuaidi100.bdindex.pojo.req.PermissionsQueryReq;

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

    PageInfo<PermissionDo> queryList(PermissionsQueryReq permissionsQueryReq);
}

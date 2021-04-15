package com.ample16.stackdemo.service;

import com.ample16.stackdemo.pojo.dto.PermissionDo;
import com.ample16.stackdemo.pojo.dto.RoleDo;
import com.ample16.stackdemo.pojo.req.PermissionAddOrUpdateReq;

/**
 * @author zefeng_lin
 * @date 2021-04-01 14:42
 * @description
 */
public interface IPermissionService {

    void addOrUpdate(PermissionAddOrUpdateReq permissionAddOrUpdateReq);

    void delete(Long permissionId);

    void findByPermissionId(Long permissionId);
}

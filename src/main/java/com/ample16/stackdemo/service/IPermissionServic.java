package com.ample16.stackdemo.service;

import com.ample16.stackdemo.pojo.dto.PermissionDo;
import com.ample16.stackdemo.pojo.dto.RoleDo;

/**
 * @author zefeng_lin
 * @date 2021-04-01 14:42
 * @description
 */
public interface IPermissionServic {
    void add(PermissionDo permissionDo);

    void update(PermissionDo permissionDo);

    void delete(Long permissionId);

    void findByPermissionId(Long permissionId);
}

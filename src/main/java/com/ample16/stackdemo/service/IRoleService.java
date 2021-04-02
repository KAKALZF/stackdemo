package com.ample16.stackdemo.service;

import com.ample16.stackdemo.pojo.dto.RoleDo;

/**
 * @author zefeng_lin
 * @date 2021-04-01 14:42
 * @description
 */
public interface IRoleService {
    void add(RoleDo roleDo);

    void update(RoleDo roleDo);

    void delete(Long roleId);

    void findByRoleId(Long roleId);
}

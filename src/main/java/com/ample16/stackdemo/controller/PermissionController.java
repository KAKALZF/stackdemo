package com.ample16.stackdemo.controller;

import com.ample16.stackdemo.pojo.ResponseBean;
import com.ample16.stackdemo.pojo.req.PermissionAddOrUpdateReq;
import com.ample16.stackdemo.pojo.req.RoleAddOrUpdateReq;
import com.ample16.stackdemo.service.serviceimpl.PermissionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zefeng_lin
 * @date 2021-04-01 15:39
 * @description
 */
@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    PermissionServiceImpl permissionService;

    @PostMapping("/addOrUpdate")
    public ResponseBean addOrUpdate(@RequestBody PermissionAddOrUpdateReq permissionAddOrUpdateReq) {
        permissionService.addOrUpdate(permissionAddOrUpdateReq);
        return ResponseBean.success();
    }
}

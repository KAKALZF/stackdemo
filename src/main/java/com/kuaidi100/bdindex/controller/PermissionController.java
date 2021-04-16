package com.kuaidi100.bdindex.controller;

import com.kuaidi100.bdindex.pojo.ResponseBean;
import com.kuaidi100.bdindex.pojo.req.PermissionAddOrUpdateReq;
import com.kuaidi100.bdindex.service.serviceimpl.PermissionServiceImpl;
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

package com.kuaidi100.bdindex.controller;

import com.kuaidi100.bdindex.pojo.ResponseBean;
import com.kuaidi100.bdindex.pojo.req.PermissionAddOrUpdateReq;
import com.kuaidi100.bdindex.sercurity.config.AuthPermit;
import com.kuaidi100.bdindex.service.serviceimpl.PermissionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

 /*   @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('opt|permission|add')")
    @AuthPermit(authName = "opt|permission|add")
    public ResponseBean add(@RequestBody PermissionAddOrUpdateReq permissionAddOrUpdateReq) {
        permissionService.addOrUpdate(permissionAddOrUpdateReq);
        return ResponseBean.success();
    }*/

    @PostMapping("/udpate")
    @PreAuthorize("hasAnyAuthority('opt|permission|udpate')")
    @AuthPermit(authName = "opt|permission|udpate", zhName = "权限|更新")
    public ResponseBean update(@RequestBody PermissionAddOrUpdateReq permissionAddOrUpdateReq) {
        permissionService.addOrUpdate(permissionAddOrUpdateReq);
        return ResponseBean.success();
    }


    @GetMapping("/load")
    @PreAuthorize("hasAnyAuthority('opt|permission|load')")
    @AuthPermit(authName = "opt|permission|load", zhName = "权限|重载")
    public ResponseBean load() {
        permissionService.loadAllPermissions();
        return ResponseBean.success();
    }


    @PostMapping("/delete")
    @PreAuthorize("hasAnyAuthority('opt|permission|delete')")
    @AuthPermit(authName = "opt|permission|delete", zhName = "权限|删除")
    public ResponseBean delete(@RequestParam("id") Long id) {
        permissionService.delete(id);
        return ResponseBean.success();
    }

}

package com.kuaidi100.bdindex.controller;

import com.kuaidi100.bdindex.pojo.ResponseBean;
import com.kuaidi100.bdindex.pojo.req.RoleAddOrUpdateReq;
import com.kuaidi100.bdindex.sercurity.config.AuthPermit;
import com.kuaidi100.bdindex.service.serviceimpl.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author zefeng_lin
 * @date 2021-04-01 15:39
 * @description
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    RoleServiceImpl roleService;

    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('opt|role|add')")
    @AuthPermit(authName = "opt|role|add", zhName = "角色新增")
    public ResponseBean add(@RequestBody RoleAddOrUpdateReq roleAddOrUpdateReq) {
        roleService.addOrUpdate(roleAddOrUpdateReq);
        return ResponseBean.success();
    }

    @PostMapping("/update")
    @PreAuthorize("hasAnyAuthority('opt|role|udpate')")
    @AuthPermit(authName = "opt|role|udpate", zhName = "角色更新")
    public ResponseBean update(@RequestBody RoleAddOrUpdateReq roleAddOrUpdateReq) {
        roleService.addOrUpdate(roleAddOrUpdateReq);
        return ResponseBean.success();
    }

    @PostMapping("/delete")
    @PreAuthorize("hasAnyAuthority('opt|role|delete')")
    @AuthPermit(authName = "opt|role|delete", zhName = "角色删除")
    public ResponseBean update(@RequestParam("id") Long id) {
        roleService.delete(id);
        return ResponseBean.success();
    }


}

package com.kuaidi100.bdindex.controller;

import com.github.pagehelper.PageInfo;
import com.kuaidi100.bdindex.pojo.ResponseBean;
import com.kuaidi100.bdindex.pojo.dto.PermissionDo;
import com.kuaidi100.bdindex.pojo.req.PermissionAddOrUpdateReq;
import com.kuaidi100.bdindex.pojo.req.PermissionsQueryReq;
import com.kuaidi100.bdindex.pojo.resp.PermissionVo;
import com.kuaidi100.bdindex.sercurity.config.AuthPermit;
import com.kuaidi100.bdindex.service.serviceimpl.PermissionServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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


    @GetMapping("/list")
    @PreAuthorize("hasAnyAuthority('opt|permission|list')")
    @AuthPermit(authName = "opt|permission|list", zhName = "权限|列表")
    public ResponseBean<HashMap<String, Object>> list(PermissionsQueryReq permissionsQueryReq) {
        PageInfo<PermissionDo> permissionDoPageInfo = permissionService.queryList(permissionsQueryReq);
        List<PermissionDo> list = permissionDoPageInfo.getList();
        ArrayList<PermissionVo> permissionVos = new ArrayList<PermissionVo>();
        for (PermissionDo permissionDo : list) {
            PermissionVo permissionVo = new PermissionVo();
            BeanUtils.copyProperties(permissionDo, permissionVo);
            permissionVos.add(permissionVo);
        }
        long total = permissionDoPageInfo.getTotal();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("list", permissionVos);
        hashMap.put("total", total);
        return ResponseBean.success().setData(hashMap);
    }
}

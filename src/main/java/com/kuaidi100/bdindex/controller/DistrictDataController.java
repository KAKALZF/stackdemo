package com.kuaidi100.bdindex.controller;

import com.kuaidi100.bdindex.pojo.ResponseBean;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zefeng_lin
 * @date 2021-04-15 16:19
 * @description
 */
@RestController
@RequestMapping("/districtData")
public class DistrictDataController {

    @RequestMapping("/getInfo")
//    @RolesAllowed({"ROLE_ADMIN"})
    @PreAuthorize("hasAnyAuthority('admins') and hasAnyAuthority('read_account') ")
    public ResponseBean getInfo() {
        return ResponseBean.success();
    }
}
package com.ample16.stackdemo.controller;

import com.ample16.stackdemo.pojo.ResponseBean;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zefeng_lin
 * @date 2021-04-15 16:21
 * @description
 */
@RestController
@RequestMapping("/routerData")
public class RouteDataController {

    @RequestMapping("/getInfo")
    @Secured("ROLE_ADMIN")
    public ResponseBean getInfo() {
        return ResponseBean.success();
    }
}

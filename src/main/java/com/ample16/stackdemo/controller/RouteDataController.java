package com.ample16.stackdemo.controller;

import com.ample16.stackdemo.pojo.ResponseBean;
import com.ample16.stackdemo.pojo.req.RouteDataQueryReq;
import com.ample16.stackdemo.pojo.resp.RouteDataApiStrVo;
import com.ample16.stackdemo.pojo.resp.RouteDataResp;
import com.ample16.stackdemo.service.serviceimpl.RouteDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zefeng_lin
 * @date 2021-04-15 16:21
 * @description
 */
@RestController
@RequestMapping("/routerData")
public class RouteDataController {
    @Autowired
    RouteDataService routeDataService;

    @RequestMapping("/getInfo")

//    @Secured("ROLE_ADMIN")
    public ResponseBean getInfo(@RequestBody RouteDataQueryReq routeDataQueryReq) {
        List<RouteDataApiStrVo> routeData = routeDataService.getRouteData(routeDataQueryReq);
        return ResponseBean.success().setData(routeData);
    }
}

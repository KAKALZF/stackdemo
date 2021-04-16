package com.ample16.stackdemo.controller;

import com.ample16.stackdemo.pojo.ResponseBean;
import com.ample16.stackdemo.pojo.req.AreaDataQueryReq;
import com.ample16.stackdemo.pojo.req.RouteDataQueryReq;
import com.ample16.stackdemo.pojo.resp.AreaDataStrVo;
import com.ample16.stackdemo.pojo.resp.RouteDataStrVo;
import com.ample16.stackdemo.service.serviceimpl.DataService;
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
@RequestMapping("/data")
public class DataController {
    @Autowired
    DataService dataService;

    @RequestMapping("/routeInfo")
//    @Secured("ROLE_ADMIN")
    public ResponseBean routeInfo(@RequestBody RouteDataQueryReq routeDataQueryReq) {
        List<RouteDataStrVo> routeData = dataService.getRouteData(routeDataQueryReq);
        return ResponseBean.success().setData(routeData);
    }


    @RequestMapping("/areaInfo")
//    @Secured("ROLE_ADMIN")
    public ResponseBean areaInfo(@RequestBody AreaDataQueryReq areaDataQueryReq) {
        List<AreaDataStrVo> routeData = dataService.getAreaData(areaDataQueryReq);
        return ResponseBean.success().setData(routeData);
    }
}

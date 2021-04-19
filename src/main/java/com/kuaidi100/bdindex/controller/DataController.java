package com.kuaidi100.bdindex.controller;

import com.kuaidi100.bdindex.pojo.ResponseBean;
import com.kuaidi100.bdindex.pojo.req.AreaDataQueryReq;
import com.kuaidi100.bdindex.pojo.req.RouteDataQueryReq;
import com.kuaidi100.bdindex.pojo.resp.AreaDataStrVo;
import com.kuaidi100.bdindex.pojo.resp.RouteDataStrVo;
import com.kuaidi100.bdindex.sercurity.config.AuthPermit;
import com.kuaidi100.bdindex.service.serviceimpl.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
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
@RequestMapping("/dataModule")
public class DataController {
    @Autowired
    DataService dataService;

    @PostMapping("/routeInfo")
    @PreAuthorize("hasAnyAuthority('module|routeInfo')")
    @AuthPermit(authName = "module|routeInfo", zhName = "线路信息模块")
    public ResponseBean routeInfo(@RequestBody RouteDataQueryReq routeDataQueryReq) {
        List<RouteDataStrVo> routeData = dataService.getRouteData(routeDataQueryReq);
        return ResponseBean.success().setData(routeData);
    }


    @PostMapping("/areaInfo")
    //@Secured("ROLE_ADMIN")
    @PreAuthorize("hasAnyAuthority('module|areaInfo')")
    @AuthPermit(authName = "module|areaInfo", zhName = "地区信息模块")
    public ResponseBean areaInfo(@RequestBody AreaDataQueryReq areaDataQueryReq) {
        List<AreaDataStrVo> routeData = dataService.getAreaData(areaDataQueryReq);
        return ResponseBean.success().setData(routeData);
    }
}

package com.ample16.stackdemo.service.serviceimpl;

import com.ample16.stackdemo.pojo.APIResponseBean;
import com.ample16.stackdemo.pojo.req.RouteDataQueryReq;
import com.ample16.stackdemo.pojo.resp.RouteDataApiStrVo;
import com.ample16.stackdemo.pojo.resp.RouteDataResp;
import com.ample16.stackdemo.pojo.resp.RouteDataVo;
import com.ample16.stackdemo.sercurity.config.FieldAuthCheck;
import com.ample16.stackdemo.util.HttpUtil;
import com.ample16.stackdemo.util.JsonMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zefeng_lin
 * @date 2021-04-16 11:15
 * @description
 */

@Service("routeDataService")
public class RouteDataService {

    @FieldAuthCheck
    public List<RouteDataApiStrVo> getRouteData(RouteDataQueryReq routeDataQueryReq) {
        String s = JsonMapper.defaultMapper().toJson(routeDataQueryReq);
        String post = HttpUtil.defaultHttp().post("http://timev2.api.kuaidi100.com/timev2/industry/index", s, "application/json");
        System.out.println("=========" + post);
        APIResponseBean<List<RouteDataResp>> apiResp = JsonMapper.defaultMapper().fromJson(post, new TypeReference<APIResponseBean<List<RouteDataResp>>>() {
        });
        List<RouteDataResp> data = apiResp.getData();
        ArrayList<RouteDataApiStrVo> routeDataApiStrVos = new ArrayList<>();
        for (RouteDataResp dataApiResp : data) {
            RouteDataApiStrVo routeDataApiStrVo = new RouteDataApiStrVo();
            routeDataApiStrVo.setCom(dataApiResp.getCom());
            routeDataApiStrVo.setCount(dataApiResp.getCount());
            routeDataApiStrVo.setExpendTime(String.valueOf(dataApiResp.getExpendTime()));
            routeDataApiStrVo.setReturnRate(dataApiResp.getReturnRate());
            routeDataApiStrVos.add(routeDataApiStrVo);
        }
        return routeDataApiStrVos;
     /*   ArrayList<RouteDataVo.RouteData> routeDatas = new ArrayList<>();
        routeDataResp.setRouteDataList(routeDatas);
        for (int i = 0; i < 10; i++) {
            RouteDataVo.RouteData routeData = new RouteDataVo.RouteData();
            routeData.setCompany("快递公司" + i);
            routeData.setRoute("线路" + i);
            routeData.setAverageTime("平均时间" + i);
            routeData.setCustomerUnitPrice("客单价" + i);
            routeDatas.add(routeData);
        }
        return routeDataResp;*/
    }
}

package com.ample16.stackdemo.service.serviceimpl;

import com.ample16.stackdemo.pojo.APIResponseBean;
import com.ample16.stackdemo.pojo.req.AreaDataQueryReq;
import com.ample16.stackdemo.pojo.req.RouteDataQueryReq;
import com.ample16.stackdemo.pojo.resp.AreaDataResp;
import com.ample16.stackdemo.pojo.resp.AreaDataStrVo;
import com.ample16.stackdemo.pojo.resp.RouteDataStrVo;
import com.ample16.stackdemo.pojo.resp.RouteDataResp;
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

@Service("dataService")
public class DataService {

    @FieldAuthCheck
    public List<RouteDataStrVo> getRouteData(RouteDataQueryReq routeDataQueryReq) {
        String s = JsonMapper.defaultMapper().toJson(routeDataQueryReq);
        String post = HttpUtil.defaultHttp().post("http://timev2.api.kuaidi100.com/timev2/industry/index", s, "application/json");
        System.out.println("=========" + post);
        APIResponseBean<List<RouteDataResp>> apiResp = JsonMapper.defaultMapper().fromJson(post, new TypeReference<APIResponseBean<List<RouteDataResp>>>() {
        });
        List<RouteDataResp> data = apiResp.getData();
        ArrayList<RouteDataStrVo> routeDataStrVos = new ArrayList<>();
        for (RouteDataResp dataApiResp : data) {
            RouteDataStrVo routeDataStrVo = new RouteDataStrVo();
            routeDataStrVo.setCom(dataApiResp.getCom());
            routeDataStrVo.setCount(dataApiResp.getCount());
            routeDataStrVo.setExpendTime(String.valueOf(dataApiResp.getExpendTime()));
            routeDataStrVo.setReturnRate(dataApiResp.getReturnRate());
            routeDataStrVo.setSignRate(dataApiResp.getSignRate());
            routeDataStrVo.setStartTime(String.valueOf(dataApiResp.getStartTime()));
            routeDataStrVo.setTransportCount(dataApiResp.getTransportCount());
            routeDataStrVo.setRoute(dataApiResp.getFrom() + "-" + dataApiResp.getTo());
            routeDataStrVo.setType(String.valueOf(dataApiResp.getType()));
            routeDataStrVos.add(routeDataStrVo);
        }
        return routeDataStrVos;
    }


    @FieldAuthCheck
    public List<AreaDataStrVo> getAreaData(AreaDataQueryReq areaDataQueryReq) {
        String s = JsonMapper.defaultMapper().toJson(areaDataQueryReq);
        String post = HttpUtil.defaultHttp().post("http://timev2.api.kuaidi100.com/timev2/industry/area", s, "application/json");
        System.out.println("=========" + post);
        APIResponseBean<List<AreaDataResp>> apiResp = JsonMapper.defaultMapper().fromJson(post, new TypeReference<APIResponseBean<List<AreaDataResp>>>() {
        });
        List<AreaDataResp> data = apiResp.getData();
        ArrayList<AreaDataStrVo> areaDataStrVos = new ArrayList<>();
        for (AreaDataResp dataApiResp : data) {
            AreaDataStrVo areaDataStrVo = new AreaDataStrVo();
            areaDataStrVo.setCom(dataApiResp.getCom());
            areaDataStrVo.setExpendTime(String.valueOf(dataApiResp.getExpendTime()));
            areaDataStrVo.setReturnRate(dataApiResp.getReturnRate());
            areaDataStrVo.setSignRate(dataApiResp.getSignRate());
            areaDataStrVo.setStartTime(String.valueOf(dataApiResp.getStartTime()));
            areaDataStrVo.setRoute(dataApiResp.getFrom() + "-" + dataApiResp.getTo());
            areaDataStrVo.setType(String.valueOf(dataApiResp.getType()));
            areaDataStrVo.setIsDeliver(dataApiResp.isDeliver() ? "Y" : "N");
            areaDataStrVo.setIsPickUp(dataApiResp.isPickUp() ? "Y" : "N");
            areaDataStrVos.add(areaDataStrVo);
        }
        return areaDataStrVos;
    }

}

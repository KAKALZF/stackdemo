package com.kuaidi100.bdindex.pojo.resp;

import com.kuaidi100.bdindex.sercurity.config.AuthPermit;
import lombok.Data;

/**
 * @author zefeng_lin
 * @date 2021-04-16 15:56
 * @description
 */
@Data
public class RouteDataStrVo implements DataStrVo {
    private String com;
    private String route;
    @AuthPermit(authName = "fieldDataAuth|route|type")
    private String type;
    @AuthPermit(authName = "fieldDataAuth|route|count")
    private String count;
    @AuthPermit(authName = "fieldDataAuth|route|startTime")
    private String startTime;
    @AuthPermit(authName = "fieldDataAuth|route|expendTime")
    private String expendTime;
    @AuthPermit(authName = "fieldDataAuth|route|transportCount")
    private String transportCount;
    @AuthPermit(authName = "fieldDataAuth|route|signRate")
    private String signRate;
    @AuthPermit(authName = "fieldDataAuth|route|returnRate")
    private String returnRate;


}

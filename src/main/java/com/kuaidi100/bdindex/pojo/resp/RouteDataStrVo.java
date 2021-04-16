package com.kuaidi100.bdindex.pojo.resp;

import com.kuaidi100.bdindex.sercurity.config.FieldAuthCheck;
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
    @FieldAuthCheck(authName = "fieldDataAuth|route|type")
    private String type;
    @FieldAuthCheck(authName = "fieldDataAuth|route|count")
    private String count;
    @FieldAuthCheck(authName = "fieldDataAuth|route|startTime")
    private String startTime;
    @FieldAuthCheck(authName = "fieldDataAuth|route|expendTime")
    private String expendTime;
    @FieldAuthCheck(authName = "fieldDataAuth|route|transportCount")
    private String transportCount;
    @FieldAuthCheck(authName = "fieldDataAuth|route|signRate")
    private String signRate;
    @FieldAuthCheck(authName = "fieldDataAuth|route|returnRate")
    private String returnRate;


}

package com.ample16.stackdemo.pojo.resp;

import lombok.Data;

/**
 * @author zefeng_lin
 * @date 2021-04-16 15:56
 * @description
 */
@Data
public class RouteDataApiStrVo {

    private String com;
    private String route;
    private String type;
    private String count;
    private String startTime;
    private String expendTime;
    private String transportCount;
    private String signRate;
    private String returnRate;


}

package com.kuaidi100.bdindex.pojo.resp;

import lombok.Data;

/**
 * @author zefeng_lin
 * @date 2021-04-16 15:56
 * @description
 */
@Data
public class RouteDataResp {

    /**
     * com : yuantong
     * from : 广东,广州市
     * to : 海南,海口市
     * type : 0
     * count : 642
     * startTime : 17
     * expendTime : 110
     * transportCount : 2.8
     * signRate : 81.00%
     * returnRate : 0.00%
     */

    private String com;
    private String from;
    private String to;
    private Integer type;
    private String count;
    private Integer startTime;
    private Integer expendTime;
    private String transportCount;
    private String signRate;
    private String returnRate;


}

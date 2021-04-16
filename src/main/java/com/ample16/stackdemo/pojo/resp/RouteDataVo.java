package com.ample16.stackdemo.pojo.resp;

import lombok.Data;

import java.util.List;

/**
 * @author zefeng_lin
 * @date 2021-04-16 11:16
 * @description
 */
@Data
public class RouteDataVo {
    List<RouteData> routeDataList;

    @Data
    public static class RouteData {
        /**
         * 线路
         */
        private String route;
        /**
         * 快递公司
         */
        private String company;
        /**
         * 平均时效
         */
        private String averageTime;
        /**
         * 客单价
         */
        private String customerUnitPrice;
    }
}

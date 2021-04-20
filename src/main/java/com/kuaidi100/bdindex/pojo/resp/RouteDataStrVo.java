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
    /**
     * 快递公司类型
     */
    private String type;
    /**
     * 开始时间
     */
    private String startTime;
    @AuthPermit(authName = "fieldDataAuth|route|expendTime", zhName = "路线数据|耗时")
    private String expendTime;
    @AuthPermit(authName = "fieldDataAuth|route|transportCount", zhName = "路线数据|转运次数")
    private String transportCount;
    @AuthPermit(authName = "fieldDataAuth|route|signRate", zhName = "路线数据|妥投率")
    private String signRate;
    @AuthPermit(authName = "fieldDataAuth|route|returnRate", zhName = "路线数据|退签率")
    private String returnRate;
    /**
     * 订单数量,暂不展示
     */
    private String count;


}

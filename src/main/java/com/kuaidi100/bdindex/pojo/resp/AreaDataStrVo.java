package com.kuaidi100.bdindex.pojo.resp;

import com.kuaidi100.bdindex.sercurity.config.AuthPermit;
import lombok.Data;

/**
 * @author zefeng_lin
 * @date 2021-04-16 15:56
 * @description
 */
@Data
public class AreaDataStrVo implements DataStrVo {
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
    @AuthPermit(authName = "fieldDataAuth|area|expendTime", zhName = "地区数据|耗时")
    private String expendTime;
    @AuthPermit(authName = "fieldDataAuth|area|signRate", zhName = "地区数据|妥投率")
    private String signRate;
    @AuthPermit(authName = "fieldDataAuth|area|returnRate", zhName = "地区数据|退签率")
    private String returnRate;
    /**
     * 是否揽件
     */
    private String isPickUp;
    /**
     * 是手运输
     */
    private String isDeliver;
}

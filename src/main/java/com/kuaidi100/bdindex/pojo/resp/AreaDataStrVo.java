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
    @AuthPermit(authName = "fieldDataAuth|area|type", zhName = "地区数据|类型")
    private String type;
    @AuthPermit(authName = "fieldDataAuth|area|startTime", zhName = "地区数据|开始时间")
    private String startTime;
    @AuthPermit(authName = "fieldDataAuth|area|expendTime",zhName = "地区数据|耗时")
    private String expendTime;
    @AuthPermit(authName = "fieldDataAuth|area|signRate")
    private String signRate;
    @AuthPermit(authName = "fieldDataAuth|area|returnRate")
    private String returnRate;
    private String isPickUp;
    private String isDeliver;
}

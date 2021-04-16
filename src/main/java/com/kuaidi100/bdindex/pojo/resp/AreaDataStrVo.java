package com.kuaidi100.bdindex.pojo.resp;

import com.kuaidi100.bdindex.sercurity.config.FieldAuthCheck;
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
    @FieldAuthCheck(authName = "fieldDataAuth|area|type")
    private String type;
    @FieldAuthCheck(authName = "fieldDataAuth|area|startTime")
    private String startTime;
    @FieldAuthCheck(authName = "fieldDataAuth|area|expendTime")
    private String expendTime;
    @FieldAuthCheck(authName = "fieldDataAuth|area|signRate")
    private String signRate;
    @FieldAuthCheck(authName = "fieldDataAuth|area|returnRate")
    private String returnRate;
    private String isPickUp;
    private String isDeliver;
}

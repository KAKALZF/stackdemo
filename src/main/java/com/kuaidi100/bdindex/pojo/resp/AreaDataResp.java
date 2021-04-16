package com.kuaidi100.bdindex.pojo.resp;

import lombok.Data;

/**
 * @author zefeng_lin
 * @date 2021-04-16 19:00
 * @description
 */
@Data
public class AreaDataResp {

    /**
     * com : shentong
     * from : 河北,石家庄市
     * to : 河北,石家庄市
     * type : 0
     * startTime : 0
     * expendTime : 0
     * signRate : 88.00%
     * returnRate : 0.05%
     * isPickUp : true
     * isDeliver : true
     */

    private String com;
    private String from;
    private String to;
    private int type;
    private int startTime;
    private int expendTime;
    private String signRate;
    private String returnRate;
    private boolean isPickUp;
    private boolean isDeliver;
}

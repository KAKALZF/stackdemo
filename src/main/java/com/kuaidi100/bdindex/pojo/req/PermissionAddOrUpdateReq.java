package com.kuaidi100.bdindex.pojo.req;

import lombok.Data;

/**
 * @author zefeng_lin
 * @date 2021-04-01 15:47
 * @description
 */
@Data
public class PermissionAddOrUpdateReq {
    private Long id;
    /**
     * 英文名称
     */
    private String name;
    /**
     * 权限类型
     */
    private Integer type;
    /**
     * 描述
     */
    private String desc;
    /**
     * 中文名称
     */
    private String zhName;

}

package com.kuaidi100.bdindex.pojo.dto;

import java.util.Date;

import lombok.Data;

/**
 * @author zefeng_lin
 * @date 2021-04-01 19:08
 * @description
 */
@Data
public class UserRoleDo {
    private Long userId;

    private Long roleId;

    private Date createTime;


}

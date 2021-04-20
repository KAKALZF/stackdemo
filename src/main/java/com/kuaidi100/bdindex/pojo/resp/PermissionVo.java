package com.kuaidi100.bdindex.pojo.resp;

import lombok.Data;

import java.util.Date;

/**
 * @author zefeng_lin
 * @date 2021-04-20 11:34
 * @description
 */
@Data
public class PermissionVo {
    private Long id;

    private String name;

    private String desc;

    private String zhName;
}

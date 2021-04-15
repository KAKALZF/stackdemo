package com.ample16.stackdemo.pojo.req;

import lombok.Data;

import java.util.List;

/**
 * @author zefeng_lin
 * @date 2021-04-01 15:47
 * @description
 */
@Data
public class PermissionAddOrUpdateReq {
    private Long id;
    private String name;
    private Integer type;

}

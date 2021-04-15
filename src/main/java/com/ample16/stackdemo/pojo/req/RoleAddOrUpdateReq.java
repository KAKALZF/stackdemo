package com.ample16.stackdemo.pojo.req;

import lombok.Data;

import java.util.List;

/**
 * @author zefeng_lin
 * @date 2021-04-14 14:48
 * @description
 */
@Data
public class RoleAddOrUpdateReq {
    private Long id;
    private String name;
    private List<Long> permissionIds;
}

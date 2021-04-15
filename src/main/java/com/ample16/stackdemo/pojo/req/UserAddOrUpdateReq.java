package com.ample16.stackdemo.pojo.req;

import lombok.Data;

import java.util.List;

/**
 * @author zefeng_lin
 * @date 2021-04-01 15:47
 * @description
 */
@Data
public class UserAddOrUpdateReq {
    private Long id;
    private String username;
    private Long clientId;
    /**
     * 角色id集合
     */
    private List<Long> roleIds;
}

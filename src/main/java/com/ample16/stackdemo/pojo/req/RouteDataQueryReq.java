package com.ample16.stackdemo.pojo.req;

import lombok.Data;

import java.util.List;

/**
 * @author zefeng_lin
 * @date 2021-04-16 15:12
 * @description
 */
@Data
public class RouteDataQueryReq {
    private List<String> com;
    private List<String> from;
    private List<String> to;
    private String dataTime;
    private Integer page;
    private Integer size;
}

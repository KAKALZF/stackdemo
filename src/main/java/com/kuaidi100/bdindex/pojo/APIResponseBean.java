package com.kuaidi100.bdindex.pojo;


import lombok.Data;

import java.io.Serializable;

/**
 * 第三方API响应
 * Created by lzf on 2019/5/10.
 */
@Data
public class APIResponseBean<T> implements Serializable {
    /**
     * 响应码
     */
    private int returnCode;

    /**
     * 响应结果描述
     */
    private String message = "";
    private String result = "";

    /**
     * 响应数据
     */
    private T data;


}

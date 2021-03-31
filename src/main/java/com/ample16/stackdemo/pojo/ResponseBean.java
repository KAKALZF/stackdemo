package com.ample16.stackdemo.pojo;



import java.io.Serializable;

/**
 * 前置响应
 * Created by lzf on 2019/5/10.
 */
public class ResponseBean<T> implements Serializable {
    /**
     * 响应码
     */
    private int code;

    /**
     * 响应结果描述
     */
    private String message = "";

    /**
     * 响应数据
     */
    private T data;

    public int getCode() {
        return code;
    }

    public ResponseBean setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ResponseBean setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public ResponseBean setData(T data) {
        this.data = data;
        return this;
    }

    public static ResponseBean success() {
        return new ResponseBean().setCode(StatusCode.C200.getCode()).setMessage(StatusCode.C200.getDes());
    }

    public static ResponseBean exception() {
        return new ResponseBean().setCode(StatusCode.C_ERROR.getCode()).setMessage(StatusCode.C_ERROR.getDes());
    }

    public static ResponseBean warn(StatusCode statusCode) {
        return new ResponseBean().setCode(statusCode.getCode()).setMessage(statusCode.getDes());
    }

    public static ResponseBean response() {
        return new ResponseBean();
    }

    public static Boolean isRespSucess(ResponseBean responseBean) {
        if (responseBean.getCode() == StatusCode.C200.getCode() && responseBean.getData() != null) {
            return true;
        }
        return false;
    }

}

package com.kuaidi100.bdindex.pojo;

/**
 * @author zefeng_lin
 * @date 2019/5/16 9:22
 */

public enum StatusCode {
    /**
     * 正常
     */
    C200(200, "success"),
    /**
     * 系统异常
     */
    C_ERROR(-1, "系统繁忙"),
    C_401(401, "账号参数错误"),
    C_402(402, "无权限"),
    C_10000(10000, "信息错误"),
    C_10001(10001, "用户未登录"),
    C_10002(10002, "请求参数错误");

    public Integer getCode() {
        return code;
    }

    public StatusCode setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getDes() {
        return des;
    }

    public StatusCode setDes(String des) {
        this.des = des;
        return this;
    }

    private Integer code;
    private String des;

    StatusCode(Integer code, String des) {
        this.code = code;
        this.des = des;
    }
}

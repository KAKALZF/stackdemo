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
    C_10000(10000, "信息错误"),
    C_10001(10001, "用户未登录"),
    C_10002(10002, "请求参数错误"),
    C_10003(10003, "验证码失效"),
    C_10004(10004, "验证码错误"),
    C_10005(10005, "信息不存在"),
    C_10006(10006, "更新数据失败"),
    C_10007(10007, "非法参数"),
    C_10008(10008, "数据已存在"),
    C_10009(10009, "图片上传错误"),
    C_10010(10010, "提现金额不能超过可提余额"),
    C_10011(10011, "提现金额必须大于或等于100元"),
    C_10012(10012, "提现中"),
    C_10013(10013, "格式不符合要求"),
    C_10014(10014, "比例累计需要等于1"),
    C_10015(10015, "签名校验不通过"),
    C_10016(10016, "数据解析异常"),
    C_10017(10017, "不支持此快递公司"),
    C_10018(10018, "每日申请提现次数不能超过3次"),
    C_10019(10019, "签名校验不通过"),
    C_10020(10020, "请勿重复提交"),
    C_10021(10021, "没有操作权限"),
    C_10022(10022, "接口调用次数已用完"),
    C_10023(10023, "接口调用排队中...请稍后再试"),
    C_10024(10024, "用户未授权"),
    C_10025(10025, "请先购买此产品"),
    C_10026(10026, "文件不存在"),
    C_10027(10027, "服务降级"),
    C_10028(10028, "年包套餐订单数量错误"),
    C_10029(10029, "服务商状态未审核"),
    C_10030(10030, "服务商状态审核不通过"),
    C_10031(10031, "服务商未启用"),
    C_10032(10032, "服务商状态错误"),
    C_10033(10033, "该单价格非免费"),
    C_10034(10034, "该邮箱已被绑定"),
    C_10035(10035, "该手机号已被绑定"),
    C_10036(10036, "取消失败"),
    C_10037(10037, "手机验证码发送失败"),
    C_10038(10038, "邮箱验证码发送失败"),
    C_10039(10039, "非法ip"),
    C_10040(10040, "查询时间区间不能跨月"),
    C_10041(10041, "重复提交"),
    C_10050(10050, "三级以上人员不能发展合伙人");

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
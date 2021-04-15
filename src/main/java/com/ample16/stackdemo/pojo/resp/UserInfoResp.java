package com.ample16.stackdemo.pojo.resp;

import lombok.Data;

import java.util.List;

/**
 * @author zefeng_lin
 * @date 2021-04-15 11:05
 * @description
 */
@Data
public class UserInfoResp {
    private List<AuthInfo> authInfos;
    private List<RoleInfo> roleInfos;

    @Data
    public static class AuthInfo {
        private Integer type;
        private String name;
    }
    @Data
    public static class RoleInfo {
        private String name;
    }
}

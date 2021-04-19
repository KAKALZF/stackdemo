package com.kuaidi100.bdindex.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author zefeng_lin
 * @date 2021-04-19 12:39
 * @description
 */
public class CommonUtil {
    public static String getCookieToken(HttpServletRequest request) {
        String token = "";
        javax.servlet.http.Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (null != c && "TOKEN".equals(c.getName())) {
                    token = c.getValue();
                    break;
                }
            }
        }
        return token;
    }
}

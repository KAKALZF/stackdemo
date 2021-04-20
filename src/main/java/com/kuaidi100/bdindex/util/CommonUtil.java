package com.kuaidi100.bdindex.util;

import com.kuaidi100.bdindex.controller.DataController;
import com.kuaidi100.bdindex.controller.PermissionController;
import com.kuaidi100.bdindex.controller.RoleController;
import com.kuaidi100.bdindex.controller.UserController;
import com.kuaidi100.bdindex.pojo.resp.AreaDataStrVo;
import com.kuaidi100.bdindex.pojo.resp.RouteDataStrVo;
import com.kuaidi100.bdindex.sercurity.config.AuthPermit;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

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

    public static List<AuthPermit> getAllAuth() {
        Class[] classes = {DataController.class, PermissionController.class, RoleController.class, UserController.class,
                AreaDataStrVo.class, RouteDataStrVo.class
        };
        ArrayList<AuthPermit> authPermits = new ArrayList<>();
        for (Class aClass : classes) {
            Field[] declaredFields = aClass.getDeclaredFields();
            Method[] declaredMethods = aClass.getDeclaredMethods();

            for (Method declaredMethod : declaredMethods) {
                if (declaredMethod.isAnnotationPresent(AuthPermit.class)) {
                    AuthPermit annotation = declaredMethod.getAnnotation(AuthPermit.class);
                    authPermits.add(annotation);
                }
            }
            for (Field declaredField : declaredFields) {
                if (declaredField.isAnnotationPresent(AuthPermit.class)) {
                    AuthPermit annotation = declaredField.getAnnotation(AuthPermit.class);
                    authPermits.add(annotation);
                }
            }
        }
        return authPermits;
    }

    public static List<String> getAllAuthStr() {
        List<AuthPermit> allAuth = getAllAuth();
        ArrayList<String> authorities = new ArrayList<>();
        allAuth.stream().forEach(authPermit -> authorities.add(authPermit.authName()));
        return authorities;
    }
}

package com.ample16.stackdemo.sercurity.config;

import com.ample16.stackdemo.pojo.resp.RouteDataApiStrVo;
import com.ample16.stackdemo.pojo.resp.RouteDataResp;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by linzefeng on 2021-04-16
 */
@Component
@Aspect
public class FieldDataAuthAopCheck {

    /**
     * 指定方法和标签
     */
    @Pointcut("@annotation(com.ample16.stackdemo.sercurity.config.FieldAuthCheck)")
    public void annotation() {
        System.out.println("annotation()");
    }


    @Around("annotation()")
    public List<RouteDataApiStrVo> around(ProceedingJoinPoint pjp) {
        System.out.println("======aop========");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        HashSet<String> authoritySet = new HashSet<>();
        for (GrantedAuthority authority : authorities) {
            String authorityStr = authority.getAuthority();
            System.out.println(authorityStr);
            authoritySet.add(authorityStr);
        }
        List<String> collect = authoritySet.stream()
                .filter(auth -> auth.startsWith("fieldDataAuth|route|"))
                .collect(Collectors.toList());
        ArrayList<String> authFields = new ArrayList<String>();
        for (String s : collect) {
            authFields.add(s.replace("fieldDataAuth|route|", ""));
        }
        List<RouteDataApiStrVo> routeDataList = null;
        try {
            routeDataList = (List<RouteDataApiStrVo>) pjp.proceed();
            for (RouteDataApiStrVo routeDataApiStrVo : routeDataList) {
                authHandler(authFields, routeDataApiStrVo);
            }
        } catch (Throwable e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return routeDataList;
    }

    /**
     * 对象字段没有权限的话就设置为无权限
     *
     * @param authorities
     * @param obj
     * @return
     */
    public void authHandler(ArrayList<String> authorities, Object obj) {
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
                String name = propertyDescriptor.getName();
                if (!authorities.contains(name)) {
                    if ("class".equals(name)) {
                        continue;
                    }
                    Method writeMethod = propertyDescriptor.getWriteMethod();
                    try {
                        writeMethod.invoke(obj, "无权限");
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<String> strings = Arrays.asList("123", "456");
        strings.stream().forEach(str -> str = str + "lzf");
        strings.stream().forEach(System.out::println);
    }

}

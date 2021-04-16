package com.ample16.stackdemo.sercurity.config;

import com.ample16.stackdemo.pojo.resp.RouteDataApiVo;
import com.ample16.stackdemo.pojo.resp.RouteDataVo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by linzefeng on 2021-04-16
 */
@Component
@Aspect
public class FieldDataAuthCheck {

    /**
     * 指定方法和标签
     */
    @Pointcut("@annotation(com.ample16.stackdemo.sercurity.config.FieldAuthCheck)")
    public void annotation() {
        System.out.println("annotation()");
    }


    @Around("annotation()")
    public List<RouteDataApiVo> around(ProceedingJoinPoint pjp) {
        System.out.println("======aop========");
        long startTime = System.currentTimeMillis();
        List<RouteDataApiVo> routeDataList = null;
        try {
            routeDataList = (List<RouteDataApiVo>) pjp.proceed();
        } catch (Throwable e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        String[] parameterNames = signature.getParameterNames();
        for (int i = 0; i < parameterNames.length; i++) {
            System.out.println("参数名:" + parameterNames[i]);
        }
        routeDataList.stream().forEach(routeData -> {
            routeData.setCom(routeData.getCom() + "权限处理");
        });
        String methodName = signature.getDeclaringTypeName() + "." + signature.getName();
        System.out.println(methodName + "方法执行了" + (endTime - startTime) + "ms");
        return routeDataList;
    }

}

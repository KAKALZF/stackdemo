package com.ample16.stackdemo.sercurity.config;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author zefeng_lin
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldAuthCheck {
    String authName() default "";
}

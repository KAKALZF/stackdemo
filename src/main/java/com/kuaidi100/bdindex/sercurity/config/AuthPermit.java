package com.kuaidi100.bdindex.sercurity.config;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author zefeng_lin
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthPermit {
    String authName() default "";

    int type() default 0;

    String desc() default "";

    String zhName() default "";
}

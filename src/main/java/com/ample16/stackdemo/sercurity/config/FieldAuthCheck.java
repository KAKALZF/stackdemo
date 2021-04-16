package com.ample16.stackdemo.sercurity.config;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface FieldAuthCheck {
    // marker annotation
}

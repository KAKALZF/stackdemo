package com.ample16.stackdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan(basePackages = "com.ample16.stackdemo.mapper")
public class StackdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(StackdemoApplication.class, args);
    }

}

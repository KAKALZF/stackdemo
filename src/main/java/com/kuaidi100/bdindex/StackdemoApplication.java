package com.kuaidi100.bdindex;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan(basePackages = "com.kuaidi100.bdindex.mapper")
public class StackdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(StackdemoApplication.class, args);
    }

}

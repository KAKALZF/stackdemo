package com.ample16.stackdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class StackdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(StackdemoApplication.class, args);
    }

}

package com.ample16.stackdemo.controller;

import com.ample16.stackdemo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private DemoService demoService;


    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

}

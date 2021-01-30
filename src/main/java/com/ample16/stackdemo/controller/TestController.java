package com.ample16.stackdemo.controller;

import com.ample16.stackdemo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class TestController {
    @Autowired
    private DemoService demoService;

    @GetMapping("/kaka")
    public String test(@RequestParam("name") String name) {
        return "hello world" + name;
    }

}

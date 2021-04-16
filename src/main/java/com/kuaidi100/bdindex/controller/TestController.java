package com.kuaidi100.bdindex.controller;

import com.kuaidi100.bdindex.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@RestController
public class TestController {
    @Autowired
    private DemoService demoService;


    @GetMapping("/hello")
    @RolesAllowed({"VIP"})
    public String hello() {
        return "hello";
    }

    @GetMapping("/admin/test1")
    public String adminTest() {
        return "adminTest";
    }

    @GetMapping("/article/test1")
    public String articleTest() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        return "articleTest";
    }

/*
    @Value("${server.port}")
    Integer port;
    @GetMapping("/set")
    public String set(HttpSession session) {
        session.setAttribute("user", "javaboy");
        return String.valueOf(port);
    }
    @GetMapping("/get")
    public String get(HttpSession session) {
        return session.getAttribute("user") + ":" + port;
    }*/

}

package com.ample16.stackdemo.controller;

import com.ample16.stackdemo.pojo.ResponseBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zefeng_lin
 * @date 2021-04-15 16:20
 * @description
 */
@RestController
@RequestMapping("/industryData")
public class IndustryDataController {
    @RequestMapping("/getInfo")
    public ResponseBean getInfo() {
        return ResponseBean.success();
    }
}

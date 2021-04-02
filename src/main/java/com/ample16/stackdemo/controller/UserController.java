package com.ample16.stackdemo.controller;

import com.ample16.stackdemo.mapper.UserMapper;
import com.ample16.stackdemo.pojo.ResponseBean;
import com.ample16.stackdemo.pojo.dto.UserDo;
import com.ample16.stackdemo.pojo.req.UserAddOrUpdateReq;
import com.ample16.stackdemo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author zefeng_lin
 * @date 2021-04-01 15:38
 * @description
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    IUserService userService;
    @Autowired
    UserMapper userMapper;

    @PostMapping("/addOrUpdate")
    public ResponseBean addOrUpdate(@RequestBody UserAddOrUpdateReq userAddOrUpdateReq) {
        userService.addOrUpdate(userAddOrUpdateReq);
        return ResponseBean.success();
    }

    @GetMapping("/add")
    public ResponseBean add() {
        UserDo user = new UserDo();
        user.setUsername("test1");
        user.setClientId(10L);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        Long add = userMapper.add(user);
        Long id = user.getId();
        return ResponseBean.success().setData(id);
    }

}

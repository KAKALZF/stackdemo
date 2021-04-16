package com.kuaidi100.bdindex.controller;

import com.kuaidi100.bdindex.mapper.UserMapper;
import com.kuaidi100.bdindex.pojo.ResponseBean;
import com.kuaidi100.bdindex.pojo.req.UserAddOrUpdateReq;
import com.kuaidi100.bdindex.pojo.resp.UserInfoVo;
import com.kuaidi100.bdindex.service.IUserService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
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

    @GetMapping("/getInfo")
    public ResponseBean getUserInfo(@RequestHeader("bdzsToken") String token) {
        System.out.println("=========" + token);
        DecodedJWT decode = JWT.decode(token);
        String subject = decode.getSubject();
        Date expiresAt = decode.getExpiresAt();
        UserInfoVo userInfo = userService.getUserInfo(Long.valueOf(subject));
        return ResponseBean.success().setData(userInfo);
    }


}

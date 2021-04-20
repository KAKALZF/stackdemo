package com.kuaidi100.bdindex.controller;

import com.kuaidi100.bdindex.mapper.UserMapper;
import com.kuaidi100.bdindex.pojo.ResponseBean;
import com.kuaidi100.bdindex.pojo.StatusCode;
import com.kuaidi100.bdindex.pojo.req.UserAddOrUpdateReq;
import com.kuaidi100.bdindex.pojo.resp.UserInfoVo;
import com.kuaidi100.bdindex.sercurity.config.AuthPermit;
import com.kuaidi100.bdindex.service.IUserService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.kuaidi100.sso.pojo.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
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

    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('opt|user|add')")
    @AuthPermit(authName = "opt|user|add", zhName = "用户|新增")
    public ResponseBean add(@RequestBody UserAddOrUpdateReq userAddOrUpdateReq) {
        userService.add(userAddOrUpdateReq);
        return ResponseBean.success();
    }

    @PostMapping("/update")
    @PreAuthorize("hasAnyAuthority('opt|user|update')")
    @AuthPermit(authName = "opt|user|update", zhName = "用户|更新")
    public ResponseBean update(@RequestBody UserAddOrUpdateReq userAddOrUpdateReq) {
        userService.update(userAddOrUpdateReq);
        return ResponseBean.success();
    }


    @GetMapping("/getInfo")
    public ResponseBean getUserInfo(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String token = "";
        for (Cookie cookie : cookies) {
            if ("TOKEN".equals(cookie.getName())) {
                token = cookie.getValue();
            }
        }
        if (StringUtils.isEmpty(token)) {
            return ResponseBean.warn(StatusCode.C_10002);
        }
        Long clientId = TokenUtils.getInst().parseToken(token);
        UserInfoVo userInfo = userService.getUserInfo(clientId);
        return ResponseBean.success().setData(userInfo);
    }


}

package com.ample16.stackdemo.config;

import com.ample16.stackdemo.pojo.ResponseBean;
import com.ample16.stackdemo.pojo.StatusCode;
import com.ample16.stackdemo.service.JwtUserService;
import com.ample16.stackdemo.util.JsonMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JsonLoginSuccessHandler implements AuthenticationSuccessHandler {

    private JwtUserService jwtUserService;

    public JsonLoginSuccessHandler(JwtUserService jwtUserService) {
        this.jwtUserService = jwtUserService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        //生成token，并把token加密相关信息缓存，具体请看实现类
        String token = jwtUserService.saveUserLoginInfo((UserDetails) authentication.getPrincipal());
        response.setHeader("Authorization", token);
        response.getWriter().write(JsonMapper.defaultMapper().toJson(ResponseBean.warn(StatusCode.C200)));
    }

}

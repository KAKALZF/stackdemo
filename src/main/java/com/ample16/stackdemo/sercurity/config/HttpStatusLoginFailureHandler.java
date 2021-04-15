package com.ample16.stackdemo.sercurity.config;

import com.ample16.stackdemo.pojo.ResponseBean;
import com.ample16.stackdemo.pojo.StatusCode;
import com.ample16.stackdemo.util.JsonMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 验证失败处理器
 */
public class HttpStatusLoginFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JsonMapper.defaultMapper().toJson(ResponseBean.warn(StatusCode.C_401)));
    }
}

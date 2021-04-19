package com.kuaidi100.bdindex.sercurity.exception;

import com.kuaidi100.bdindex.pojo.ResponseBean;
import com.kuaidi100.bdindex.pojo.StatusCode;
import com.kuaidi100.bdindex.util.JsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 访问受限处理器
 */
@Slf4j
public class AccessDeniedAuthenticationHandler implements AccessDeniedHandler {


    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException {
        httpServletResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.getWriter().write(JsonMapper.defaultMapper().toJson(ResponseBean.warn(StatusCode.C_402)));
    }

}

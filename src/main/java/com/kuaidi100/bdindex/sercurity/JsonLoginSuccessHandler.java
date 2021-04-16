package com.kuaidi100.bdindex.sercurity;

import com.kuaidi100.bdindex.pojo.ResponseBean;
import com.kuaidi100.bdindex.pojo.StatusCode;
import com.kuaidi100.bdindex.util.JsonMapper;
import com.kuaidi100.bdindex.util.JwtTokenUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class JsonLoginSuccessHandler implements AuthenticationSuccessHandler {

    private LoginUserService loginUserService;

    public JsonLoginSuccessHandler(LoginUserService loginUserService) {
        this.loginUserService = loginUserService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        //生成token返回
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        //密码为空,不会设置到authentication里面
        String password = userDetails.getPassword();
        //这里原本是用户密码,使用固定方式的加密的私钥即可
        Date date = new Date(System.currentTimeMillis() + 3600 * 1000);  //设置1小时后过期
        String token = JwtTokenUtil.encode(username, "bdzs@" + username, date);
        response.setHeader("Authorization", token);
        response.getWriter().write(JsonMapper.defaultMapper().toJson(ResponseBean.warn(StatusCode.C200)));
    }

}

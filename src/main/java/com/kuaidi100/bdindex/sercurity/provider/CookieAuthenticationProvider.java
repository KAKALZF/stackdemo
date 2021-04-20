package com.kuaidi100.bdindex.sercurity.provider;

import com.kuaidi100.bdindex.sercurity.token.CookieAuthenticationToken;
import com.kuaidi100.bdindex.sercurity.userservice.CookieUserService;
import com.kuaidi100.sso.pojo.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CookieAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private CookieUserService userService;

    public CookieAuthenticationProvider(CookieUserService userService) {
        this.userService = userService;
    }

    /**
     * 根据提交过来的信息(已被封装成Authentication)做认证
     *
     * @param authentication
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String token1 = ((CookieAuthenticationToken) authentication).getToken();

        Long clientId = TokenUtils.getInst().parseToken(token1);
        //根据用户名获取数据库里的信息,然后和用户所传过来的信息比对
        UserDetails user = userService.loadUserByUsername(String.valueOf(clientId));
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        CookieAuthenticationToken token = new CookieAuthenticationToken(user, token1, user.getAuthorities());
        return token;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(CookieAuthenticationToken.class);
    }

}

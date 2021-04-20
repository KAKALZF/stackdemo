package com.kuaidi100.bdindex.sercurity.userservice;


import com.kuaidi100.bdindex.pojo.resp.UserInfoVo;
import com.kuaidi100.bdindex.service.IUserService;
import com.kuaidi100.bdindex.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("cookieUserService")
public class CookieUserService implements UserDetailsService {

    @Autowired
    IUserService userService;

    public CookieUserService() {
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //如果用户拥有管理员角色,则加载所有权限
        //根据接收到的用户名来查找用户信息
//        username = "178812931";
        UserInfoVo userInfo = userService.getUserInfo(Long.valueOf(username));
        List<UserInfoVo.RoleInfo> roleInfos = userInfo.getRoleInfos();
        List<UserInfoVo.AuthInfo> authInfos = userInfo.getAuthInfos();
        final List<String> roles = new ArrayList<>();
        List<String> authorities = new ArrayList<>();
        for (UserInfoVo.RoleInfo roleInfo : roleInfos) {
            roles.add(roleInfo.getName());
        }
        if (roles.contains("ADMIN")) {
            authorities = CommonUtil.getAllAuthStr();
        } else {
            for (UserInfoVo.AuthInfo authInfo : authInfos) {
                authorities.add(authInfo.getName());
            }
        }
        //密码参数无意义
        return User.builder().username(username).password("bdzs@" + username)
                .roles(roles.toArray(new String[roles.size()]))
                .authorities(authorities.toArray(new String[authorities.size()]))
                .build();
    }


    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        System.out.println(passwordEncoder.encode("123456"));
        UserDetails build = User.builder().username("kaka").password(passwordEncoder.encode("123456"))
                .roles("USER")
//                .authorities("/viewAndOp")
                .build();
        System.out.println("user");
        System.out.println(BCrypt.gensalt());
    }

}

package com.kuaidi100.bdindex.sercurity.userservice;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Date;

public class CookieUserService implements UserDetailsService {


    public CookieUserService() {
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //如果用户拥有管理员角色,则加载所有权限
        //根据接收到的用户名来查找用户信息
//        username = "178812931";
        username = "9527";
        ArrayList<String> authorities = new ArrayList<String>();
        authorities.add("module|routeInfo");
        authorities.add("fieldDataAuth|route|com");
        authorities.add("fieldDataAuth|route|route");
        authorities.add("fieldDataAuth|route|expendTime");
        authorities.add("fieldDataAuth|route|transportCount");
        authorities.add("fieldDataAuth|route|signRate");
        authorities.add("fieldDataAuth|route|returnRate");
        String[] strings = authorities.toArray(new String[authorities.size()]);
        return User.builder().username(username).password("bdzs@" + username)
                .roles("USER", "ADMIN")
                .authorities(strings)
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

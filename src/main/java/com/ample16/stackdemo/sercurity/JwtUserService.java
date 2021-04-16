package com.ample16.stackdemo.sercurity;


import java.util.Date;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

public class JwtUserService implements UserDetailsService {


    public JwtUserService() {
    }

    public UserDetails getUserLoginInfo(String username) {
        UserDetails user = loadUserByUsername(username);
        //将salt放到password字段返回
        return user;
    }

    public String saveUserLoginInfo(UserDetails user) {
        System.out.println("saveUserLoginInfo==============");
        String salt = "bdzs@9527"; //BCrypt.gensalt();  正式开发时可以调用该方法实时生成加密的salt
        Algorithm algorithm = Algorithm.HMAC256(salt);
        Date date = new Date(System.currentTimeMillis() + 3600 * 1000);  //设置1小时后过期
        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(date)
                .withIssuedAt(new Date())
                .sign(algorithm);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据接收到的用户名来查找用户信息
        username = "9527";
        return User.builder().username(username).password("bdzs@" + username)
                .roles("USER","ADMIN")
                .authorities("admins", "read_account")
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

    public void deleteUserLoginInfo(String username) {
        System.out.println("==========deleteUserLoginInfo");
    }
}

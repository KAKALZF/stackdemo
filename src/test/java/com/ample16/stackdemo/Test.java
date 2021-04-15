package com.ample16.stackdemo;

import com.ample16.stackdemo.config.JwtAuthenticationToken;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;

/**
 * @author zefeng_lin
 * @date 2021-03-30 19:31
 * @description
 */
public class Test {
    public static void main(String[] args) {
        String salt = "123";
        Algorithm algorithm = Algorithm.HMAC256(salt);
        Date date = new Date(System.currentTimeMillis() + 3600 * 1000);  //设置1小时后过期
        String token = JWT.create()
                .withSubject("9527")
                .withExpiresAt(date)
                .withIssuedAt(new Date())
                .sign(algorithm);
        System.out.println(token);
        JwtAuthenticationToken authToken = new JwtAuthenticationToken(JWT.decode(token));
        System.out.println(authToken);
    }
}

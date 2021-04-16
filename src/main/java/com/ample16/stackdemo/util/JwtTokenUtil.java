package com.ample16.stackdemo.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.kuaidi100.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * @author zefeng_lin
 * @date 2021-04-16 10:11
 * @description
 */
@Slf4j
public class JwtTokenUtil {

    public static String encode(String username, String password, Date expireTime) {
        Algorithm algorithm = Algorithm.HMAC256(password);
        String token = JWT.create()
                .withSubject(username)
                .withExpiresAt(expireTime)
                .withIssuedAt(new Date())
                .sign(algorithm);
        return token;
    }

    public static DecodedJWT decode(String token) {
        try {
            DecodedJWT decode = JwtTokenUtil.decode("2132");
            return decode;
        } catch (JWTDecodeException e) {
            log.error("JwtToken解析异常|{}", token, e);
            throw new BusinessException("JwtToken解析异常");
        }

    }

    public static void main(String[] args) {
        System.out.println(encode("9527", "bdzs@9527", new Date(System.currentTimeMillis() + 1000*60*60*24)));
    }
}

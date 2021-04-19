package com.kuaidi100.bdindex;

import com.kuaidi100.bdindex.sercurity.token.JwtAuthenticationToken;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.kuaidi100.sso.pojo.TokenUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

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
        System.out.println("token:" + token);
        JwtAuthenticationToken authToken = new JwtAuthenticationToken(JWT.decode(token));
        System.out.println(authToken);
        DecodedJWT decode = JWT.decode(token);
        System.out.println("decodeToken:" + decode.getToken());


    }

    @org.junit.Test
    public void test1() {
//        System.out.println(System.currentTimeMillis() + 3600 * 1000);
        Date date = new Date(1618500161201L);
        String encryptSalt = "123";
        String username = "kaka";
        Algorithm algorithm = Algorithm.HMAC256(encryptSalt);
        Algorithm algorithm2 = Algorithm.HMAC256("1234");
        JWTVerifier verifier = JWT.require(algorithm)
                .withSubject(username)
                .build();

        String token = JWT.create()
                .withSubject("kaka")
                .withExpiresAt(date)
                .withIssuedAt(new Date())
                .sign(algorithm2);
        DecodedJWT decode = JWT.decode(token);
        DecodedJWT verify = verifier.verify(token);
        System.out.println(verifier.verify(token));
    }

    @org.junit.Test
    public void test2() {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        System.out.println(passwordEncoder.encode("123456"));
        UserDetails build = User.builder().username("kaka").password(passwordEncoder.encode("123456"))
                .roles("USER")
//                .authorities("/viewAndOp")
                .build();
        System.out.println("user");
        System.out.println(BCrypt.gensalt());
        System.out.println(BCrypt.gensalt());
    }


    @org.junit.Test
    public void test3() {
//        Long cilentId = TokenUtils.getInst().parseToken("a63EcxDsxyMxgR67eiJtavxqZ7am6lAepd49JwEujzA");
        Long cilentId = TokenUtils.getInst().parseToken("1231");
        System.out.println(cilentId);
    }
}

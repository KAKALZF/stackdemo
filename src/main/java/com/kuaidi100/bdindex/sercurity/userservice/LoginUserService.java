package com.kuaidi100.bdindex.sercurity.userservice;


import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

public class LoginUserService implements UserDetailsService {

    private PasswordEncoder passwordEncoder;

    public LoginUserService() {
        //默认使用 bcrypt， strength=10
        this.passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据接收到的用户名来查找用户信息,注意这里的passwordEncoder和DaoAuthenticationProvider验证时所以用的加密器需要保持一致
        return User.builder().username("bdzh").password(passwordEncoder.encode("bdzs@123"))
                .roles("USER")
                .authorities("admins", "read_account")
                .build();
    }

}

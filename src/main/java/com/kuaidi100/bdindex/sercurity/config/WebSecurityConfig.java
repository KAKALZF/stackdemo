package com.kuaidi100.bdindex.sercurity.config;

import java.util.Arrays;

import com.kuaidi100.bdindex.sercurity.exception.AccessDeniedAuthenticationHandler;
import com.kuaidi100.bdindex.sercurity.filter.OptionsRequestFilter;
import com.kuaidi100.bdindex.sercurity.handler.JsonLoginSuccessHandler;
import com.kuaidi100.bdindex.sercurity.handler.JwtRefreshSuccessHandler;
import com.kuaidi100.bdindex.sercurity.handler.TokenClearLogoutHandler;
import com.kuaidi100.bdindex.sercurity.provider.CookieAuthenticationProvider;
import com.kuaidi100.bdindex.sercurity.provider.JwtAuthenticationProvider;
import com.kuaidi100.bdindex.sercurity.userservice.CookieUserService;
import com.kuaidi100.bdindex.sercurity.userservice.JwtUserService;
import com.kuaidi100.bdindex.sercurity.userservice.LoginUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.header.Header;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 登录流程:
 * 用户提交登录数据-->MyUsernamePasswordAuthenticationFilter拦截用户提价的信息,封装为UsernamePasswordAuthenticationToken,
 * -->交给DaoAuthenticationProvider,DaoAuthenticationProvider#retrieveUser方法调用JwtUserService#loadUserByUsername方法,加载系统用户信息,
 * 验证信息,失败抛异常,信息找正确则设置相关权限信息到UserDetails
 * -->认证错误交由HttpStatusLoginFailureHandler处理(返回异常),正确则交由JsonLoginSuccessHandler处理(设置用户加密信息到响应头,下次带上相关信息便于验证)
 * <p>
 * 验权流程:
 * JwtAuthenticationFilter过滤器,
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/image/**").permitAll()
                .antMatchers("/admin/**").hasAnyRole("ADMIN")
                .antMatchers("/article/**").hasRole("USER")
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                //设置访问失败处理器
                .accessDeniedHandler(accessDeniedAuthenticationHandler())
                .and()
                .csrf().disable()
                .formLogin().disable()
                .sessionManagement().disable()
                .cors()
                .and()
                .headers().addHeaderWriter(new StaticHeadersWriter(Arrays.asList(
                new Header("Access-control-Allow-Origin", "*"),
                new Header("Access-Control-Expose-Headers", "Authorization"))))
                .and()
                .addFilterAfter(new OptionsRequestFilter(), CorsFilter.class)
                .apply(new JsonLoginConfigurer<>()).loginSuccessHandler(jsonLoginSuccessHandler())
                .and()
                //jwt,token处理
                .apply(new JwtLoginConfigurer<>()).tokenValidSuccessHandler(jwtRefreshSuccessHandler()).permissiveRequestUrls("/logout")
                .and()
                .apply(new CookieLoginConfigurer<>())
                .and()
                .logout()
                //默认就是"/logout"
                //.logoutUrl("/logout")
                .addLogoutHandler(tokenClearLogoutHandler())
                .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler())
                .and()
                .sessionManagement().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //两种认证方式
        auth.authenticationProvider(daoAuthenticationProvider())
                .authenticationProvider(cookieAuthenticationProvider())
                .authenticationProvider(jwtAuthenticationProvider());
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean("jwtAuthenticationProvider")
    protected AuthenticationProvider jwtAuthenticationProvider() {
        return new JwtAuthenticationProvider(jwtUserService());
    }

    @Bean("daoAuthenticationProvider")
    protected AuthenticationProvider daoAuthenticationProvider() throws Exception {
        //这里会默认使用BCryptPasswordEncoder比对加密后的密码，注意要跟createUser时保持一致
        DaoAuthenticationProvider daoProvider = new DaoAuthenticationProvider();
        //登录的写死一个用户,本系统不需要在本系统登录,只需要通过和管理后台约定好请求的头部来识别用户即可
        daoProvider.setUserDetailsService(new LoginUserService());
        return daoProvider;
    }

    @Override
    protected UserDetailsService userDetailsService() {
        return new JwtUserService();
    }

    @Bean("jwtUserService")
    protected JwtUserService jwtUserService() {
        return new JwtUserService();
    }

    @Bean
    protected JsonLoginSuccessHandler jsonLoginSuccessHandler() {
        return new JsonLoginSuccessHandler(new LoginUserService());
    }

    @Bean
    protected JwtRefreshSuccessHandler jwtRefreshSuccessHandler() {
        return new JwtRefreshSuccessHandler(jwtUserService());
    }

    @Bean
    protected TokenClearLogoutHandler tokenClearLogoutHandler() {
        return new TokenClearLogoutHandler(jwtUserService());
    }

    @Bean
    protected CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "HEAD", "OPTION"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.addExposedHeader("Authorization");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


    @Bean
    protected AccessDeniedAuthenticationHandler accessDeniedAuthenticationHandler() {
        return new AccessDeniedAuthenticationHandler();
    }

    protected CookieAuthenticationProvider cookieAuthenticationProvider() {
        return new CookieAuthenticationProvider(new CookieUserService());
    }

}

package org.example.configuration;

import org.example.entity.SysUser;
import org.example.handler.JwtAccessDeniedHandler;
import org.example.handler.JwtAuthenticationEntryPoint;
import org.example.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Description:org.example.configuration
 * @Date:2023/11/11
 * @Author:谢锦创
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private SysUserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        // 管理跨域攻击
        httpSecurity.csrf().disable();
        // 关闭session，为什么，有什么作用
        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // 请求都需要认证之后才能访问，除了白名单以外的资源
        httpSecurity.authorizeRequests().anyRequest().authenticated();
        // 关闭缓存
        httpSecurity.headers().cacheControl();
        // token过滤器，校验token
        httpSecurity.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint);
        //没有权限访问资源，自定义返回结果
        httpSecurity.exceptionHandling().accessDeniedHandler(jwtAccessDeniedHandler);

//        httpSecurity.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).accessDeniedHandler(jwtAccessDeniedHandler)

    }

    /**
     * 一般用于配置白名单
     * 白名单：没有权限
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    @Bean // 这个为什么要放到bean里面
    protected UserDetailsService userDetailsService() {
        // TODO: 2023/11/12 用一个lambda函数实现
        // 如果user不为空，返回user，否则跑一个 UserNameNotFoundException
        return username -> {
            SysUser sysUser = userService.findByUsername(username);
            if (sysUser != null) {
                return sysUser;
            } else {
                throw new UsernameNotFoundException("用户名或密码错误");
            }
        };
    }

    /**
     * 自定义登录逻辑  这个逻辑是在干什么
     *
     * @param auth the {@link AuthenticationManagerBuilder} to use
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder);
    }
}

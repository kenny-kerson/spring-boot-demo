package com.kenny.springbootdemo.springsecurity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    /**
     * URI Pattern별 authentication 설정을 셋팅
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .mvcMatchers("/", "/info", "/h2-console").permitAll()
                .mvcMatchers("/admin").hasRole("ADMIN")
                .anyRequest().authenticated();
        http.formLogin();
        http.httpBasic();
    }

    /**
     * 인메모리 유저 추가
     * - 패스워드 {noop} prefix 추가는, spring security 4가 되면서,
     * - 기본 패스워드 인코더가 NoOpPasswordEncoder에서 BCryptPasswordEncoder로 변경되면서 생김.
     *
     * => DAO 유저 추가 로직으로 인해 주석처리!!
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("kenny").password("{noop}1234").roles("USER").and()
                .withUser("admin").password("{noop}!@#$").roles("ADMIN");
    }
}

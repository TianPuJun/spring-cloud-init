package com.tz.sso.config;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.client.RestTemplate;

/**
 * @author tz
 * @Classname LoginConfig
 * @Description 登录配置
 * @Date 2020-01-02 10:00
 */
@Configuration
@EnableOAuth2Sso

public class LoginConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login/**").authenticated()
                .and().csrf().disable();


    }
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}

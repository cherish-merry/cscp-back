package com.cscp.auth.config;

import com.cscp.auth.authentication.OauthAuthenticationSuccessHandler;
import com.cscp.auth.properties.SecurityProperties;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Configuration
@EnableWebSecurity
@Order(-1)
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    UserDetailsService userDetailsService;

    @Autowired
    SecurityProperties securityProperties;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    OauthAuthenticationSuccessHandler oauthAuthenticationSuccessHandler() {
        return new OauthAuthenticationSuccessHandler();
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        LinkedList<String> ignorePath = new LinkedList<>();
        ignorePath.addAll(securityProperties.getBrowser().getIgnorePath());
        ignorePath.addAll(Arrays.asList(securityProperties.getBrowser().getLoginProcessingUrl(), securityProperties.getBrowser().getRedirectUri(), securityProperties.getBrowser().getLoginPage()));
        String[] ignorePathArray = ignorePath.toArray(new String[0]);
        http
                .formLogin().permitAll()
                .loginPage(securityProperties.getBrowser().getRedirectUri())
                .loginProcessingUrl(securityProperties.getBrowser().getLoginProcessingUrl())
                .successHandler(oauthAuthenticationSuccessHandler())
                .and()
                .authorizeRequests()
                .antMatchers(ignorePathArray)
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf()
                .disable();
    }


    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        // 设置userDetailsService
        provider.setUserDetailsService(userDetailsService);
        provider.setHideUserNotFoundExceptions(false);
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        return provider;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(daoAuthenticationProvider());
    }
}

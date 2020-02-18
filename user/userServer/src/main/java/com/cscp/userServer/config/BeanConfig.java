package com.cscp.userServer.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.cscp.common.security.ResourceServerConfig;
import com.cscp.common.security.SecurityProperties;
import com.cscp.common.utils.GlobalExceptionAdvice;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author chen kezhuo
 * @discription
 * @date 2020/1/2 - 14:50
 */
@Configuration
public class BeanConfig {
    @Bean
    public GlobalExceptionAdvice globalExceptionAdvice() {
        return new GlobalExceptionAdvice();
    }

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}

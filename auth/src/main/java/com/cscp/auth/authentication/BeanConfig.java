package com.cscp.auth.authentication;

import com.cscp.common.utils.GlobalExceptionAdvice;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

/**
 * @author chen kezhuo
 * @discription
 * @date 2020/4/7 - 21:47
 */
@Configurable
public class BeanConfig {
    @Bean
    GlobalExceptionAdvice globalExceptionAdvice(){
        return new GlobalExceptionAdvice();
    }
}

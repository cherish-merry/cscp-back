package com.cscp.documentServer.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.cscp.common.security.TokenExtractService;
import com.cscp.common.utils.FeignClientInterceptor;
import com.cscp.common.utils.GlobalExceptionAdvice;
import com.cscp.common.utils.RequestAttributeHystrixConcurrencyStrategy;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;

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


    @Bean
    public FeignClientInterceptor feignClientInterceptor() {
        return new FeignClientInterceptor();
    }

    @Bean
    public RequestAttributeHystrixConcurrencyStrategy requestAttributeHystrixConcurrencyStrategy() {
        return new RequestAttributeHystrixConcurrencyStrategy();
    }

    @Bean
    public TokenExtractService tokenExtractService(){
        return new TokenExtractService();
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //  单个数据大小
        factory.setMaxFileSize("1024000KB");
//////        /// 总上传数据大小
//        factory.setMaxRequestSize("102400KB");
        return factory.createMultipartConfig();
    }
}

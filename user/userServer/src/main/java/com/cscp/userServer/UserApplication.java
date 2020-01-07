package com.cscp.userServer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author chen kezhuo
 * @discription
 * @date 2019/8/23 - 11:08
 */
@SpringBootApplication
@EnableEurekaClient
//@EnableDistributedTransaction
@EnableSwagger2
@EnableCircuitBreaker
@EnableFeignClients
@MapperScan("com.cscp.userServer.dao.mapper")
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class,args);
    }
}

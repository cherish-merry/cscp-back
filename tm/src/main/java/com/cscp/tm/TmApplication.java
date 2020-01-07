package com.cscp.tm;

import com.codingapi.txlcn.tm.config.EnableTransactionManagerServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author chen kezhuo
 * @discription
 * @date 2019/9/7 - 21:36
 */
@SpringBootApplication
@EnableTransactionManagerServer
@EnableEurekaClient
public class TmApplication {
    public static void main(String[] args) {
        SpringApplication.run(TmApplication.class,args);
    }
}

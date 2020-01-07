package com.cscp.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author chen kezhuo
 * @discription
 * @date 2019/10/23 - 10:37
 */
@Data
@ConfigurationProperties(prefix = "swagger")
public class GatewayProperties {
    private List<String>  ignoreModules;
}

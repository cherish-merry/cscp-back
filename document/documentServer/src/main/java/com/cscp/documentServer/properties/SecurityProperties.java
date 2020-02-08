package com.cscp.documentServer.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author chen kezhuo
 * @discription
 * @date 2020/2/8 - 18:08
 */
@Data
@ConfigurationProperties(prefix = "colstu.security")
public class SecurityProperties {
    private List<String> ignorePaths;
}

package com.cscp.common.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

/**
 * @author chen kezhuo
 * @discription
 * @date 2020/2/8 - 18:08
 */
@Data
@ConfigurationProperties(prefix = "colstu.security")
public class SecurityProperties {
    private List<String> ignorePaths;
    private Map<String, String> hasRole;
    private Map<String, String> hasAnyRole;
}

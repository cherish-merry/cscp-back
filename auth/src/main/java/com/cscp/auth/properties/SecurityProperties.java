package com.cscp.auth.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author chen kezhuo
 * @discription
 * @date 2019/10/20 - 16:06
 */
@Data
@ConfigurationProperties(prefix = "colstu.security")
public class SecurityProperties {
    BrowserProperties browser =new BrowserProperties();
    private OAuth2Properties oauth2 = new OAuth2Properties();
}

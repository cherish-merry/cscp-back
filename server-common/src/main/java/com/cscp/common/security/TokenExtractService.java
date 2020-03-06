package com.cscp.common.security;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

import javax.servlet.http.HttpServletRequest;

/**
 * @author chen kezhuo
 * @discription
 * @date 2020/3/6 - 12:12
 */
public class TokenExtractService {
    @Autowired
    TokenStore tokenStore;

    public String currentUserId(HttpServletRequest httpServletRequest) {
        String authorization = httpServletRequest.getHeader("Authorization");
        String token = StringUtils.substringAfter(authorization, "Bearer ");
        OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(token);
        return (String) oAuth2AccessToken.getAdditionalInformation().get("UID");
    }
}

package com.cscp.auth.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chen kezhuo
 * @discription
 * @date 2020/3/5 - 17:39
 */
@Component
public class UserIdTokenEnhancer implements TokenEnhancer {
    @Autowired
    TokenStore tokenStore;

    @Autowired
    JwtAccessTokenConverter jwtAccessTokenConverter;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Map<String, Object> info = new HashMap<>();
        info.put("sign","ckz");
        accessToken.getValue();
        Map<String,Object> tmp =new HashMap<>();
        OAuth2Authentication oAuth2Authentication = jwtAccessTokenConverter.extractAuthentication(tmp);
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
        return null;
    }
}

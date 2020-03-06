package com.cscp.auth.authentication;

import com.cscp.auth.client.UserClient;
import dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
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
    private static final String PUBLIC_KEY = "public-key.txt";

    @Autowired
    TokenStore tokenStore;

    @Autowired
    JwtAccessTokenConverter jwtAccessTokenConverter;

    @Autowired
    UserClient userClient;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        if (authentication.getUserAuthentication().getPrincipal() instanceof User) {
            Map<String, Object> info = new HashMap<>();
            User user = (User) authentication.getUserAuthentication().getPrincipal();
            UserDto userDto = userClient.getUserByUsername(user.getUsername());
            if (userDto != null) {
                info.put("UID", userDto.getId());
            }
            ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
        }

        return accessToken;
    }
}

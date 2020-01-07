package com.cscp.auth.controller;

import com.cscp.auth.support.AuthUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author chen kezhuo
 * @discription
 * @date 2019/10/30 - 16:47
 */
@RestController
public class AuthController {
    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    AuthorizationServerTokenServices authorizationServerTokenServices;

    @PostMapping("/oauth/refreshToken")
    public OAuth2AccessToken refreshToken(HttpServletRequest httpServletRequest) throws IOException {
        String header = httpServletRequest.getHeader("Authorization");
        String refreshToken = httpServletRequest.getParameter("refresh_token");
        if(StringUtils.isEmpty(refreshToken)){
            throw new InvalidTokenException("refresh_token is not exist");
        }
        if (header != null && header.toLowerCase().startsWith("basic ")) {
            String[] tokens = AuthUtils.extractAndDecodeHeader(header, httpServletRequest);
            assert tokens.length == 2;
            String clientId = tokens[0];
            String clientSecret = tokens[1];
            ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);
            if (clientDetails == null) {
                throw new UnapprovedClientAuthenticationException("clientId 不匹配..");
            } else if (!StringUtils.equals(clientDetails.getClientSecret(), clientSecret)) {
                throw new UnapprovedClientAuthenticationException("clientSecret 不匹配..");
            }
            TokenRequest tokenRequest = new TokenRequest(new HashMap<>(), clientId, clientDetails.getScope(), "custom");
            return authorizationServerTokenServices.refreshAccessToken(refreshToken, tokenRequest);
        }
        return null;
    }
}

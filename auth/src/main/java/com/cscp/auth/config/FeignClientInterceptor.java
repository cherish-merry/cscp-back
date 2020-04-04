package com.cscp.auth.config;

import com.cscp.auth.service.TokenService;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.temporal.TemporalUnit;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;

//@Component
public class FeignClientInterceptor implements RequestInterceptor {
    @Autowired
    AuthorizationServerTokenServices authorizationServerTokenServices;

    @Autowired
    private ClientDetailsService clientDetailsService;

    private String token;

    private LocalDateTime expireTime=LocalDateTime.now();

    @Override
    public void apply(RequestTemplate requestTemplate) {
        if(expireTime.isBefore(LocalDateTime.now())){
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken("auth", null, AuthorityUtils.commaSeparatedStringToAuthorityList("admin,ROLE_USER"));
            ClientDetails clientDetails = clientDetailsService.loadClientByClientId("auth");
            TokenRequest tokenRequest = new TokenRequest(new HashMap<>(), "app", Arrays.asList("app"), "custom");
            OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(clientDetails);
            OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, authentication);
            OAuth2AccessToken accessToken = authorizationServerTokenServices.createAccessToken(oAuth2Authentication);
            expireTime=LocalDateTime.now().plusSeconds(accessToken.getExpiresIn());
            token=accessToken.getValue();
        }
        requestTemplate.header("Authorization", token);
    }
}

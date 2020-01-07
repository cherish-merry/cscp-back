package com.cscp.auth.authentication;

import com.cscp.auth.properties.SecurityProperties;
import com.cscp.auth.support.AuthUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author chen kezhuo
 * @discription
 * @date 2019/10/12 - 9:37
 */
@Slf4j
public class OauthAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    private static final String TOKEN_PREFIX="OAUTH_TOKEN_PREFIX:";

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    AuthorizationServerTokenServices authorizationServerTokenServices;

//    @Autowired
//    RedisTemplate<Object,Object> redisTemplate;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        String header = httpServletRequest.getHeader("Authorization");
        if (header != null && header.toLowerCase().startsWith("basic ")) {
            String[] tokens = AuthUtils.extractAndDecodeHeader(header, httpServletRequest);
            assert tokens.length == 2;
            String clientId = tokens[0];
            String clientSecret = tokens[1];
            ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);
            if(clientDetails==null){
                throw new UnapprovedClientAuthenticationException("clientId 不匹配..");
            }else if(!StringUtils.equals(clientDetails.getClientSecret(),clientSecret)){
                throw new UnapprovedClientAuthenticationException("clientSecret 不匹配..");
            }
            TokenRequest tokenRequest = new TokenRequest(new HashMap<>(), clientId, clientDetails.getScope(), "custom");
            OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(clientDetails);
            OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, authentication);
            OAuth2AccessToken accessToken = authorizationServerTokenServices.createAccessToken(oAuth2Authentication);
//            redisTemplate.opsForValue().set(TOKEN_PREFIX+accessToken.getAdditionalInformation().get("jti"),accessToken,clientDetails.getAccessTokenValiditySeconds(), TimeUnit.SECONDS);
            httpServletResponse.setContentType("application/json;charset=UTF-8");
            httpServletResponse.getWriter().write(objectMapper.writeValueAsString(accessToken));
        }
    }
}

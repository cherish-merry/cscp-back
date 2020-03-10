package com.cscp.common.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

import java.util.Map;

/**
 * @author chen kezhuo
 * @discription
 * @date 2020/3/10 - 10:51
 */
public class UserInfoUtil {
    public static String getUID() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthenticationDetails auth2AuthenticationDetails = (OAuth2AuthenticationDetails) authentication.getDetails();
        Map<String, Object> extraInfo = (Map<String, Object>) auth2AuthenticationDetails.getDecodedDetails();
        return (String) extraInfo.get("UID");
    }

    public static String getUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) authentication;
        return oAuth2Authentication.getUserAuthentication().getPrincipal().toString();
    }
}

package com.cscp.auth.properties;

import lombok.Data;

@Data
public class OAuth2ClientProperties {
    private String clientId;

    private String clientSecret;

    private int accessTokenValidateSeconds = 60 * 60 * 24;

    private int refreshTokenValidateSeconds = 60 * 60 * 24 * 30;
}
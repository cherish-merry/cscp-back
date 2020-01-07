package com.cscp.auth.properties;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
public class OAuth2Properties {
    private List<OAuth2ClientProperties> clients = new LinkedList<>();
}
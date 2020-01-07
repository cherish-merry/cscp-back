package com.cscp.auth.properties;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;

/**
 * @author chen kezhuo
 * @discription
 * @date 2019/10/20 - 16:07
 */
@Data
public class BrowserProperties {
    private String loginProcessingUrl="/authentication/form";
    private String redirectUri="/authentication/require";
    private String loginPage="/signIn.html";
    private List<String> ignorePath=new LinkedList<>();
}

package com.cscp.auth.config;

import com.cscp.auth.properties.SecurityProperties;
import com.cscp.common.support.Result;
import com.cscp.common.support.ResultUtil;
import com.cscp.common.utils.ViewException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author chen kezhuo
 * @discription
 * @date 2019/10/12 - 0:00
 */
@RestController
@Slf4j
public class BrowserSecurityController {
    @Autowired
    private SecurityProperties securityProperties;

    @RequestMapping("/authentication/require")
    public Result requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return ResultUtil.error(500,"invalid username password or other reason  can't ge token");
    }
}

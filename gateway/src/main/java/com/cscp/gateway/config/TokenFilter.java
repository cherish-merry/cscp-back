package com.cscp.gateway.config;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * @author chen kezhuo
 * @discription
 * @date 2019/8/25 - 20:01
 */
//@Component
public class TokenFilter extends ZuulFilter {
    private static final String TOKEN_PREFIX="OAUTH_TOKEN_PREFIX:";

//    @Autowired
//    RedisTemplate<Object, Object> redisTemplate;

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return PRE_DECORATION_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
//        String authorization = request.getHeader("Authorization");
//        DefaultOAuth2AccessToken accessToken = (DefaultOAuth2AccessToken) redisTemplate.opsForValue().get(TOKEN_PREFIX+authorization);
//        MutableHttpServletRequest mutableRequest = new MutableHttpServletRequest(request);
//        if (accessToken == null) {
//            currentContext.setSendZuulResponse(false);
//            currentContext.setResponseStatusCode(HttpStatus.SC_UNAUTHORIZED);
//            return null;
//        }
//        mutableRequest.putHeader("Authorization", accessToken.getValue());
        return null;
    }
}

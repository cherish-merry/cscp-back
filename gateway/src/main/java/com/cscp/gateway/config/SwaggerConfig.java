package com.cscp.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author chen kezhuo
 * @discription
 * @date 2019/10/4 - 14:55
 */
@Configuration
@EnableSwagger2
@EnableConfigurationProperties(GatewayProperties.class)
public class SwaggerConfig {
    @Autowired
    RouteLocator routeLocator;

    @Autowired
    GatewayProperties gatewayProperties;

    @Component
    @Primary
    public class DocumentationConfig implements SwaggerResourcesProvider {
        @Override
        public List<SwaggerResource> get() {
            List resources = new ArrayList<>();
            routeLocator.getRoutes().forEach(route -> {
                //动态获取
                if (!gatewayProperties.getIgnoreModules().contains(route.getId())) {
                    resources.add(swaggerResource(route.getId(), route.getFullPath().replace("**", "v2/api-docs"), "2.0"));
                }
            });
            return resources;
        }

        private SwaggerResource swaggerResource(String name, String location, String version) {
            SwaggerResource swaggerResource = new SwaggerResource();
            swaggerResource.setName(name);
            swaggerResource.setLocation(location);
            swaggerResource.setSwaggerVersion(version);
            return swaggerResource;
        }
    }
}

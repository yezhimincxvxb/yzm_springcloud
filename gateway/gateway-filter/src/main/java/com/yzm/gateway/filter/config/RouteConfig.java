package com.yzm.gateway.filter.config;

import com.yzm.gateway.filter.filter.MyGatewayFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfig {

    @Bean
    public RouteLocator customerRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r
                        .path("/get")
                        .filters(f -> f
                                .filter(new MyGatewayFilter())
                                .addRequestHeader("addRequest", "yzm")
                                .addResponseHeader("addResponse", "yzm"))
                        .uri("http://httpbin.org:80")
                        .id("myFilterTest")
                )
                .build();
    }

}

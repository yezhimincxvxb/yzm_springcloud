package com.yzm.gateway.route.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfig {

    @Bean
    public RouteLocator customerRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("to_local8002",
                        r -> r
                                .path("/local/payment/**")
                                .filters(f -> f.
                                        stripPrefix(1)
                                )
                                .uri("http://localhost:8002/")
                )
                .route("to_baidu",
                        r -> r
                                .path("/bai/**", "/du/**")
                                .filters(f -> f.
                                        stripPrefix(1)
                                )
                                .uri("https://www.baidu.com/")
                )
                .build();
    }
}

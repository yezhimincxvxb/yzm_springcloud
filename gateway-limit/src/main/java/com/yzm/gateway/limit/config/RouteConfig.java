package com.yzm.gateway.limit.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class RouteConfig {

    @Bean
    public RouteLocator customerRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                // 根据令牌限流
                .route(p -> p
                        .path("/ip/**")
                        .filters(f -> f
                                .stripPrefix(1)
                                .filter(new RateLimitByIpGatewayFilter(3,1, Duration.ofSeconds(1))))
                        .uri("http://httpbin.org:80"))
                .build();

        /**
         * 实际项目中，除以上实现的限流方式，还可能会：
         * 一、在上文的基础上，增加配置项，控制每个路由的限流指标，并实现动态刷新，从而实现更加灵活的管理。
         * 二、实现不同维度的限流。
         * 例如：
         *
         * 对请求的目标 URL 进行限流（例如：某个 URL 每分钟只允许调用多少次）
         * 对客户端的访问 IP 进行限流（例如：某个 IP 每分钟只允许请求多少次）
         * 对某些特定用户或者用户组进行限流（例如：非 VIP 用户限制每分钟只允许调用 100 次某个 API 等）
         * 多维度混合的限流。此时，就需要实现一些限流规则的编排机制（与、或、非等关系）
         */
    }

}

package com.yzm.gateway.limit.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 用于限流的键的解析器的 Bean 对象名字。
 * 它使用 SpEL 表达式根据#{@beanName}从 Spring 容器中获取 Bean 对象。
 * 默认情况下，使用PrincipalNameKeyResolver，以请求认证的java.security.Principal作为限流键。
 */
@Component
public class RemoteAddrKeyResolver implements KeyResolver {

    @Override
    public Mono<String> resolve(ServerWebExchange exchange) {
        // 根据IP地址限流
        String hostAddress = exchange.getRequest().getRemoteAddress().getAddress().getHostAddress();
        System.out.println("hostAddress = " + hostAddress);

        // 根据请求地址限流
        String uriPath = exchange.getRequest().getURI().getPath();
        System.out.println("uriPath = " + uriPath);

        // 根据用户ID
        //String userId = exchange.getRequest().getQueryParams().getFirst("userId");
        return Mono.just(uriPath);
    }


}

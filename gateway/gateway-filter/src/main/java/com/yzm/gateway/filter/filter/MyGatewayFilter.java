package com.yzm.gateway.filter.filter;


import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 自定义过滤器
 * 测试：调用服务的响应时间
 */
@Slf4j
@Component
public class MyGatewayFilter implements GatewayFilter, Ordered {

    private static final String TIME_BEGIN = "timeBegin";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //请求执行前 前置过滤
        exchange.getAttributes().put(TIME_BEGIN, System.currentTimeMillis());

        //请求执行后
        return chain.filter(exchange).then(
                Mono.fromRunnable(() -> {
                    // 后置过滤
                    Object startTime = exchange.getAttribute(TIME_BEGIN);
                    if (startTime != null) {
                        ServerHttpRequest request = exchange.getRequest();
                        log.info("请求ID：" + request.getId());
                        log.info("请求IP地址：" + request.getRemoteAddress());
                        log.info("请求方式：" + request.getMethod());
                        log.info("请求路径：" + request.getPath());
                        log.info("请求参数：" + request.getQueryParams());
                        ServerHttpResponse response = exchange.getResponse();
                        log.info("响应状态："+response.getStatusCode());
                        log.info("耗时: " + (System.currentTimeMillis() - (Long) startTime) + "ms");
                    }
                })
        );
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}

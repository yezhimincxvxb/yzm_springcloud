package com.yzm.gateway.filter.filter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.style.ToStringCreator;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

/**
 * 自定义过滤器工厂
 */
@Slf4j
@Component
public class MyGatewayFilterFactory extends AbstractGatewayFilterFactory<MyGatewayFilterFactory.NameConfig> {

    private static final String TIME_BEGIN = "timeBegin";
    private static final String KEY = "moreLog";

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList(KEY);
    }

    public MyGatewayFilterFactory() {
        super(NameConfig.class);
    }

    @Override
    public GatewayFilter apply(NameConfig config) {
        return (exchange, chain) -> {
            exchange.getAttributes().put(TIME_BEGIN, System.currentTimeMillis());
            return chain.filter(exchange).then(
                    Mono.fromRunnable(() -> {
                        if (config.isMoreLog()) {
                            log.info("==============打印完整日志====================");
                            ServerHttpRequest request = exchange.getRequest();
                            log.info("请求ID：" + request.getId());
                            log.info("请求IP地址：" + request.getRemoteAddress());
                            log.info("请求方式：" + request.getMethod());
                            log.info("请求路径：" + request.getPath());
                            log.info("请求参数：" + request.getQueryParams());
                            ServerHttpResponse response = exchange.getResponse();
                            log.info("响应状态：" + response.getStatusCode());
                            log.info("耗时: " + (System.currentTimeMillis() - (Long) exchange.getAttribute(TIME_BEGIN)) + "ms");
                        } else {
                            log.info("==============打印简化日志====================");
                            ServerHttpRequest request = exchange.getRequest();
                            log.info("请求方式：" + request.getMethod());
                            log.info("请求路径：" + request.getPath());
                            log.info("请求参数：" + request.getQueryParams());
                            log.info("耗时: " + (System.currentTimeMillis() - (Long) exchange.getAttribute(TIME_BEGIN)) + "ms");
                        }
                    })
            );
        };
    }

    @Data
    static class NameConfig {

        private boolean moreLog;

        boolean isMoreLog() {
            return moreLog;
        }
    }

    public static class NameValueConfig {
        private String name;
        private String value;

        public NameValueConfig() {
        }

        public String getName() {
            return this.name;
        }

        public NameValueConfig setName(String name) {
            this.name = name;
            return this;
        }

        public String getValue() {
            return this.value;
        }

        public NameValueConfig setValue(String value) {
            this.value = value;
            return this;
        }

        public String toString() {
            return (new ToStringCreator(this)).append("name", this.name).append("value", this.value).toString();
        }
    }

}

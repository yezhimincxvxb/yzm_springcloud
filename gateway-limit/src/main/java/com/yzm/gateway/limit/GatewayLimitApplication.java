package com.yzm.gateway.limit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class GatewayLimitApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayLimitApplication.class, args);
    }
}

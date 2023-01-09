package com.yzm.openfeign.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableEurekaClient
public class OpenFeignPaymentApplication {
    public static void main(String[] args) {
        SpringApplication.run(OpenFeignPaymentApplication.class, args);
    }
}

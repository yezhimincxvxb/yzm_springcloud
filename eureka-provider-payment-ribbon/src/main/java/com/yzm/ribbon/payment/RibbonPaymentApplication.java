package com.yzm.ribbon.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class RibbonPaymentApplication {
    public static void main(String[] args) {
        SpringApplication.run(RibbonPaymentApplication.class, args);
    }
}

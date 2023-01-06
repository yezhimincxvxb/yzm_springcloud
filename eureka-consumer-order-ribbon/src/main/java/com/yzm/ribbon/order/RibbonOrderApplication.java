package com.yzm.ribbon.order;

import com.yzm.ribbon.rule.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@RibbonClient(name = "EUREKA-PROVIDER-PAYMENT-RIBBON-SERVICE", configuration = MySelfRule.class) // 注释后恢复默认轮询策略
public class RibbonOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(RibbonOrderApplication.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

package com.yzm.order.controller;

import com.yzm.commons.api.RespResult;
import com.yzm.commons.dto.PaymentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderController {

    /**
     * 未引入 spring-cloud-starter-netflix-eureka-client 前，使用 restTemplate 进行服务间的调用
     * 需要指定明确的IP地址端口，无法进行负载均衡且不易于维护
     * 引入 spring-cloud-starter-netflix-eureka-client 依赖后，
     * 访问hello接口会报异常：No instances available for localhost
     */
    public static final String PAYMENT_URL = "http://localhost:8001/payment";

    /**
     * 微服务：通过服务名称进行服务调用
     */
    public static final String PAYMENT_URL_V2 = "http://EUREKA-PROVIDER-PAYMENT-SERVICE/payment";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/hello")
    public RespResult<?> hello() {
        return restTemplate.getForObject(PAYMENT_URL + "/hello", RespResult.class);
    }

    @GetMapping("/hello2")
    public RespResult<?> hello2() {
        return restTemplate.getForObject(PAYMENT_URL_V2 + "/hello", RespResult.class);
    }

    @GetMapping("/getById/{id}")
    public RespResult<?> getById(@PathVariable Long id) {
        System.out.println("1234");
        System.out.println("1234");
        return restTemplate.getForObject(PAYMENT_URL_V2 + "/getById/" + id, RespResult.class);
    }

    @GetMapping("/list")
    public RespResult<?> list() {
        return restTemplate.getForObject(PAYMENT_URL_V2 + "/list", RespResult.class);
    }

    @GetMapping("/save")
    public RespResult<?> save(@RequestParam("username") String username) {
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setUsername(username);
        paymentDto.setSerialNo(UUID.randomUUID().toString());
        return restTemplate.postForObject(PAYMENT_URL_V2 + "/save", paymentDto, RespResult.class);
    }

}

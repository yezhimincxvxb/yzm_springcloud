package com.yzm.consul.order.controller;

import com.yzm.commons.api.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/consumer")
public class OrderController {

    // 这里服务必须小写跟服务名一致
    public static final String PAYMENT_URL = "http://consul-provider-payment-service/payment";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/hello")
    public CommonResult<?> hello() {
        return restTemplate.getForObject(PAYMENT_URL + "/hello", CommonResult.class);
    }

}

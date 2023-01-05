package com.yzm.ribbon.controller;

import com.yzm.commons.api.CommonResult;
import com.yzm.commons.entity.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/consumer")
public class OrderController {

    public static final String PAYMENT_URL = "http://PROVIDER-PAYMENT-SERVICE/provider/payment";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/hello")
    public CommonResult<?> hello() {
        return restTemplate.getForObject(PAYMENT_URL + "/hello", CommonResult.class);
    }

}

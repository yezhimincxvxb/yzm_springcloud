package com.yzm.ribbon.order.controller;

import com.yzm.commons.api.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/order")
public class OrderController {

    public static final String PAYMENT_URL = "http://RIBBON-PROVIDER-PAYMENT-SERVICE/payment";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/hello")
    public RespResult<?> hello() {
        return restTemplate.getForObject(PAYMENT_URL + "/hello", RespResult.class);
    }

    @GetMapping(value = "/discovery")
    public Object discovery() { return restTemplate.getForObject(PAYMENT_URL + "/discovery", Object.class);
    }
}

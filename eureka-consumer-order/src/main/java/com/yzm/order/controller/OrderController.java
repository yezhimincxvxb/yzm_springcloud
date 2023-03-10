package com.yzm.order.controller;

import com.yzm.commons.api.CommonResult;
import com.yzm.commons.entity.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/order")
public class OrderController {

//    public static final String PAYMENT_URL = "http://localhost:8001/payment";
    public static final String PAYMENT_URL = "http://EUREKA-PROVIDER-PAYMENT-SERVICE/payment";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/hello")
    public CommonResult<?> hello() {
        return restTemplate.getForObject(PAYMENT_URL + "/hello", CommonResult.class);
    }

    @GetMapping("/save")
    public CommonResult<?> save(Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL + "/save", payment, CommonResult.class);
    }

    @GetMapping("/getById/{id}")
    public CommonResult<?> getById(@PathVariable Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/getById/" + id, CommonResult.class);
    }

}

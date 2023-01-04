package com.yzm.oder80.controller;

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

    public static final String PAYMENT_URL = "http://localhost:8001/payment8001/payment";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/save")
    public CommonResult<?> save(Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL + "/save", payment, CommonResult.class);
    }

    @GetMapping("/getById/{id}")
    public CommonResult<?> getById(@PathVariable Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/getById/" + id, CommonResult.class);
    }

}

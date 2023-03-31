package com.yzm.consul.payment.controller;

import com.yzm.commons.api.CommonResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/hello")
    public CommonResult<String> hello() {
        return CommonResult.success("hello " + port + "！UUID：" + UUID.randomUUID());
    }

}

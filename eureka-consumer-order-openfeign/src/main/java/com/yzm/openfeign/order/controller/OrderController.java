package com.yzm.openfeign.order.controller;

import com.yzm.commons.api.CommonResult;
import com.yzm.openfeign.order.feign.OrderFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderFeign orderFeign;

    @GetMapping("/hello")
    public CommonResult<?> hello() {
        return orderFeign.hello();
    }

    @GetMapping("/timeout")
    public CommonResult<?> timeout() {
        return orderFeign.timeout();
    }
}

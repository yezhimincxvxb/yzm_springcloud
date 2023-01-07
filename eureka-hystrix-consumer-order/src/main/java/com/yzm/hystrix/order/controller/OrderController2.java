package com.yzm.hystrix.order.controller;

import com.yzm.commons.api.CommonResult;
import com.yzm.hystrix.order.feign.OrderFeign2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/order2")
public class OrderController2 {

    @Value("${server.port}")
    private String port;

    @Resource
    private OrderFeign2 orderFeign;

    @GetMapping("/hello")
    public CommonResult<?> hello() {
        return orderFeign.hello();
    }

    @GetMapping("/timeout")
    public CommonResult<String> timeout() {
        return orderFeign.timeout();
    }

}

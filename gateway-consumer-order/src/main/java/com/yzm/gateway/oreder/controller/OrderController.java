package com.yzm.gateway.oreder.controller;

import com.yzm.commons.api.CommonResult;
import com.yzm.gateway.oreder.feign.OrderFeign;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderFeign orderFeign;

    @GetMapping("/hello")
    public CommonResult<String> hello() {
        return orderFeign.hello();
    }

    @GetMapping("/timeout/{millis}")
    public CommonResult<String> timeout(@PathVariable("millis") long millis) throws InterruptedException {
        return orderFeign.timeout(millis);
    }

    @GetMapping("/getById/{id}")
    public CommonResult<String> getById(@PathVariable("id") Integer id) {
        return orderFeign.getById(id);
    }

}

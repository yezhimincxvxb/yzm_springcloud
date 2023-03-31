package com.yzm.sleuth.order.controller;

import com.yzm.commons.api.CommonResult;
import com.yzm.sleuth.order.feign.OrderFeign;
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
    public CommonResult<?> hello() {
        return orderFeign.hello();
    }

    @GetMapping("/timeout/{millis}/{flag}")
    public CommonResult<String> timeout(@PathVariable("millis") long millis, @PathVariable("flag") int flag) throws InterruptedException {
        return orderFeign.timeout(millis, flag);
    }

}

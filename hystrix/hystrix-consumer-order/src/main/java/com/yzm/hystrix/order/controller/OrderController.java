package com.yzm.hystrix.order.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.yzm.commons.api.CommonResult;
import com.yzm.hystrix.order.feign.OrderFeign;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.UUID;

@RestController
@RequestMapping("/order")
@DefaultProperties(defaultFallback = "globalFallback")
public class OrderController {

    @Value("${server.port}")
    private String port;

    @Resource
    private OrderFeign orderFeign;

    // 正常服务
    @GetMapping("/hello")
    public CommonResult<String> hello() {
        return orderFeign.hello();
    }

    // 服务降级
    @GetMapping("/self/timeout/{millis}/{flag}")
    @HystrixCommand(fallbackMethod = "fallbackMethod", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public CommonResult<String> selfTimeout(@PathVariable("millis") long millis, @PathVariable("flag") int flag) throws InterruptedException {
        Thread.sleep(millis);

        if (flag == 1) {
            int i = 1 / 0;
        }
        return orderFeign.hello();
    }

    public CommonResult<String> fallbackMethod(long millis, int flag) throws InterruptedException {
        return CommonResult.fail("服务端口：" + port + ",线程：" + Thread.currentThread().getName() + ",UUID：" + UUID.randomUUID());
    }

    @HystrixCommand
    @GetMapping("/timeout/{millis}/{flag}")
    public CommonResult<String> timeout(@PathVariable("millis") long millis, @PathVariable("flag") int flag) throws InterruptedException {
        return orderFeign.timeout(millis, flag);
    }

    public CommonResult<String> globalFallback() {
        return CommonResult.fail("全局异常回滚：" + port + ", 线程：" + Thread.currentThread().getName() + ",UUID：" + UUID.randomUUID());
    }

    // 服务熔断
    @GetMapping("/fuse/{id}")
    @HystrixCommand(fallbackMethod = "circuitBreakerBack", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")
    })
    public CommonResult<String> circuitBreaker(@PathVariable("id") Integer id) {
        if (id < 0) throw new RuntimeException("******id 不能负数");
        String serialNumber = UUID.randomUUID().toString();
        return CommonResult.success("请求成功 " + serialNumber);
    }

    public CommonResult<String> circuitBreakerBack(@PathVariable("id") Integer id) {
        return CommonResult.fail("id 不能负数，请稍后再试，/(ToT)/~~ id:" + id);
    }
}

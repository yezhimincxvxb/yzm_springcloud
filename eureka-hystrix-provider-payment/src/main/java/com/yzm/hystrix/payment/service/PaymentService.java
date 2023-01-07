package com.yzm.hystrix.payment.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.yzm.commons.api.CommonResult;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @HystrixCommand(fallbackMethod = "fallbackTimeout", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    public CommonResult<String> timeout(String port)  {
        // 超时
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        // 异常
//        int i = 1 / 0;
        return CommonResult.success("normal " + port + ", 线程：" + Thread.currentThread().getName());
    }

    public CommonResult<String> fallbackTimeout(String port) {
        return CommonResult.success("payment " + port + ", 线程：" + Thread.currentThread().getName());
    }
}

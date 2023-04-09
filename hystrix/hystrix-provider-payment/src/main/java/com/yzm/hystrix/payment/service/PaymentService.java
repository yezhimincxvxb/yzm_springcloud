package com.yzm.hystrix.payment.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.yzm.commons.api.RespResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class PaymentService {

    @HystrixCommand(fallbackMethod = "fallbackMethod", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public RespResult<String> timeout(String port, long millis, int flag) throws InterruptedException {
        if (millis < 0L) {
            throw new IllegalArgumentException("超时参数millis不能小于0");
        }

        Thread.sleep(millis);

        if (flag == 1) {
            int i = 1 / 0;
        }
        return RespResult.success("服务端口：" + port + ",线程：" + Thread.currentThread().getName() + ",UUID：" + UUID.randomUUID());
    }

    public RespResult<String> fallbackMethod(String port, long millis, int flag) throws InterruptedException {
        log.info("port：{}, millis：{}, flag：{}", port, millis, flag);
        return RespResult.fail("服务端口：" + port + ",线程：" + Thread.currentThread().getName() + ",UUID：" + UUID.randomUUID());
    }
}

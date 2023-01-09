package com.yzm.openfeign.payment.controller;

import com.yzm.commons.api.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Yzm
 * @since 2023/01/04
 */
@Slf4j
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/hello")
    public CommonResult<String> hello() {
        return CommonResult.success("服务端口：" + port + ",UUID：" + UUID.randomUUID());
    }

    @GetMapping("timeout/{millis}")
    public CommonResult<String> timeout(@PathVariable("millis") long millis) throws InterruptedException {
        if (millis < 0L) {
            throw new IllegalArgumentException("超时参数millis不能小于0");
        }
        Thread.sleep(millis);
        return CommonResult.success("服务端口：" + port + ",超时时间：" + millis + ",UUID：" + UUID.randomUUID());
    }


}

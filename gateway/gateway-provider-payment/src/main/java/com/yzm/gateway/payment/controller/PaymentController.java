package com.yzm.gateway.payment.controller;

import com.yzm.commons.api.CommonResult;
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
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Value("${server.port}")
    private String port;


    @GetMapping("/hello")
    public CommonResult<String> hello() {
        return CommonResult.success("hello " + port + ",UUID：" + UUID.randomUUID());
    }

    @GetMapping("/timeout/{millis}")
    public CommonResult<String> timeout(@PathVariable("millis") long millis) throws InterruptedException {
        Thread.sleep(millis);
        return CommonResult.success("timeout：" + port + ", millis：" + millis + ", UUID：" + UUID.randomUUID());
    }

    @GetMapping("/getById/{id}")
    public CommonResult<String> getById(@PathVariable("id") Integer id) {
        if (id < 0) throw new RuntimeException("******id 不能负数");
        return CommonResult.success("getById：" + port + ", params:" + id + ", UUID：" + UUID.randomUUID());
    }

}

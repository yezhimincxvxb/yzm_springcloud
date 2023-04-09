package com.yzm.gateway.payment.controller;

import com.yzm.commons.api.RespResult;
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
    public RespResult<String> hello() {
        return RespResult.success("hello " + port + ",UUID：" + UUID.randomUUID());
    }

    @GetMapping("/timeout/{millis}")
    public RespResult<String> timeout(@PathVariable("millis") long millis) throws InterruptedException {
        Thread.sleep(millis);
        return RespResult.success("timeout：" + port + ", millis：" + millis + ", UUID：" + UUID.randomUUID());
    }

    @GetMapping("/getById/{id}")
    public RespResult<String> getById(@PathVariable("id") Integer id) {
        if (id < 0) throw new RuntimeException("******id 不能负数");
        return RespResult.success("getById：" + port + ", params:" + id + ", UUID：" + UUID.randomUUID());
    }

}

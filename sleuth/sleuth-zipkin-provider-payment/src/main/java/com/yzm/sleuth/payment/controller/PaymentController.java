package com.yzm.sleuth.payment.controller;

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

    @GetMapping("/timeout/{millis}/{flag}")
    public CommonResult<String> timeout(@PathVariable("millis") long millis, @PathVariable("flag") int flag) throws InterruptedException {
        if (millis < 0L) {
            throw new IllegalArgumentException("超时参数millis不能小于0");
        }
        Thread.sleep(millis);
        if (flag == 1) {
            int i = 1 / 0;
        }
        return CommonResult.success("服务端口：" + port + ",超时时间：" + millis + ",UUID：" + UUID.randomUUID());
    }


}

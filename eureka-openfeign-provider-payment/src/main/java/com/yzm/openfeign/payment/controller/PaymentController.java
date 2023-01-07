package com.yzm.openfeign.payment.controller;

import com.yzm.commons.api.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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
        return CommonResult.success("hello " + port + ",UUID：" +UUID.randomUUID());
    }

    @GetMapping("timeout")
    public CommonResult<String> timeout() throws InterruptedException {
        Thread.sleep(3000);
        return CommonResult.success("timeout " + port + ",UUID：" +UUID.randomUUID());
    }


}

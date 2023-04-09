package com.yzm.hystrix.payment.controller;

import com.yzm.commons.api.RespResult;
import com.yzm.hystrix.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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

    @Resource
    private PaymentService paymentService;

    @GetMapping("/hello")
    public RespResult<String> hello() {
        return RespResult.success("服务端口：" + port + ",UUID：" + UUID.randomUUID());
    }

    @GetMapping("/timeout/{millis}/{flag}")
    public RespResult<String> timeout(@PathVariable("millis") long millis, @PathVariable("flag") int flag) throws InterruptedException {
        return paymentService.timeout(port, millis, flag);
    }


}

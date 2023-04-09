package com.yzm.payment.controller;

import com.yzm.commons.api.RespResult;
import com.yzm.commons.dto.PaymentDto;
import com.yzm.commons.vo.PaymentVo;
import com.yzm.payment.service.IPaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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

    @Resource
    private IPaymentService paymentService;

    @Value("${server.port}")
    private String port;

    @GetMapping("/hello")
    public RespResult<String> hello() {
        return RespResult.success("hello " + port + ",UUID：" + UUID.randomUUID());
    }

    @GetMapping("/getById/{id}")
    public RespResult<PaymentVo> getById(@PathVariable Long id) {
        return RespResult.success(paymentService.getById(id));
    }

    @GetMapping("/list")
    public RespResult<List<PaymentVo>> list() {
        return RespResult.success(paymentService.list());
    }

    @PostMapping("/save")
    public RespResult<Boolean> save(@RequestBody PaymentDto paymentDto) {
        return RespResult.success(paymentService.save(paymentDto));
    }

}

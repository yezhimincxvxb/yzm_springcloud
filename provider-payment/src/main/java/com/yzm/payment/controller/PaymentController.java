package com.yzm.payment.controller;

import com.yzm.commons.api.CommonResult;
import com.yzm.commons.entity.Payment;
import com.yzm.payment.service.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Yzm
 * @since 2023/01/04
 */
@RestController
@RequestMapping("/provider/payment")
public class PaymentController {

    @Autowired
    private IPaymentService paymentService;

    @Value("${server.port}")
    private String port;

    @GetMapping("/hello")
    public CommonResult<String> hello() {
        System.out.println("1234");
        return CommonResult.success("hello " + port);
    }

    @PostMapping("/save")
    public CommonResult<Boolean> save(@RequestBody Payment payment) {
        boolean save = paymentService.save(payment);
        if (save) return CommonResult.success(save);
        return CommonResult.fail(save);
    }

    @GetMapping("/getById/{id}")
    public CommonResult<Payment> getById(@PathVariable Long id) {
        Payment payment = paymentService.getById(id);
        if (payment != null) return CommonResult.success(payment);
        return CommonResult.fail(payment);
    }

}

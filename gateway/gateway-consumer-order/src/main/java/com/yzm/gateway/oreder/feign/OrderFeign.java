package com.yzm.gateway.oreder.feign;

import com.yzm.commons.api.CommonResult;
import com.yzm.gateway.oreder.feign.back.OrderFeignBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "GATEWAY-PROVIDER-PAYMENT-SERVICE", path = "/payment", fallback = OrderFeignBack.class)
public interface OrderFeign {

    @GetMapping("/hello")
    CommonResult<String> hello();

    @GetMapping("/timeout/{millis}")
    CommonResult<String> timeout(@PathVariable("millis") long millis) throws InterruptedException;

    @GetMapping("/getById/{id}")
    CommonResult<String> getById(@PathVariable("id") Integer id);
}

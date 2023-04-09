package com.yzm.gateway.oreder.feign;

import com.yzm.commons.api.RespResult;
import com.yzm.gateway.oreder.feign.back.OrderFeignBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "GATEWAY-PROVIDER-PAYMENT-SERVICE", path = "/payment", fallback = OrderFeignBack.class)
public interface OrderFeign {

    @GetMapping("/hello")
    RespResult<String> hello();

    @GetMapping("/timeout/{millis}")
    RespResult<String> timeout(@PathVariable("millis") long millis) throws InterruptedException;

    @GetMapping("/getById/{id}")
    RespResult<String> getById(@PathVariable("id") Integer id);
}

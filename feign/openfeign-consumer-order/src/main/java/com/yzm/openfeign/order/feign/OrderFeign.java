package com.yzm.openfeign.order.feign;

import com.yzm.commons.api.RespResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "FEIGN-PROVIDER-PAYMENT-SERVICE", contextId = "payment-core", path = "/payment")
public interface OrderFeign {

    @GetMapping("/hello")
    RespResult<String> hello();

    @GetMapping("timeout/{millis}")
    RespResult<String> timeout(@PathVariable("millis") long millis) throws InterruptedException;
}

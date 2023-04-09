package com.yzm.sleuth.order.feign;

import com.yzm.commons.api.RespResult;
import com.yzm.sleuth.order.feign.back.OrderFeignBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "SLEUTH-PROVIDER-PAYMENT-SERVICE", path = "/payment", fallback = OrderFeignBack.class)
public interface OrderFeign {

    @GetMapping("/hello")
    RespResult<String> hello();

    @GetMapping("/timeout/{millis}/{flag}")
    RespResult<String> timeout(@PathVariable("millis") long millis, @PathVariable("flag") int flag) throws InterruptedException;
}

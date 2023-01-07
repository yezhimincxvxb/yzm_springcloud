package com.yzm.hystrix.order.feign;

import com.yzm.commons.api.CommonResult;
import com.yzm.hystrix.order.feign.back.OrderFeignBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "EUREKA-PROVIDER-PAYMENT-HYSTRIX-SERVICE", path = "/payment", fallback = OrderFeignBack.class)
public interface OrderFeign2 {

    @GetMapping("/hello")
    CommonResult<String> hello();

    @GetMapping("timeout")
    CommonResult<String> timeout();
}

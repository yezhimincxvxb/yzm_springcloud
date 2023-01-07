package com.yzm.hystrix.order.feign;

import com.yzm.commons.api.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "EUREKA-PROVIDER-PAYMENT-HYSTRIX-SERVICE", path = "/payment")
public interface OrderFeign {

    @GetMapping("/hello")
    CommonResult<String> hello();

    @GetMapping("timeout")
    CommonResult<String> timeout();
}

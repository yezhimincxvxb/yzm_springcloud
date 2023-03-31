package com.yzm.hystrix.order.feign;

import com.yzm.commons.api.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "HYSTRIX-PROVIDER-PAYMENT-SERVICE", path = "/payment")
public interface OrderFeign {

    @GetMapping("/hello")
    CommonResult<String> hello();

    @GetMapping("/timeout/{millis}/{flag}")
    CommonResult<String> timeout(@PathVariable("millis") long millis, @PathVariable("flag") int flag) throws InterruptedException;
}

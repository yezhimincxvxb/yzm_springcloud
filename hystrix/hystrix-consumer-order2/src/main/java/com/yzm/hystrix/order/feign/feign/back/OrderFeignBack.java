package com.yzm.hystrix.order.feign.feign.back;

import com.yzm.commons.api.CommonResult;
import com.yzm.hystrix.order.feign.feign.OrderFeign;
import org.springframework.stereotype.Component;

@Component
public class OrderFeignBack implements OrderFeign {
    @Override
    public CommonResult<String> hello() {
        return CommonResult.fail("服务宕机，请稍后再试");
    }

    @Override
    public CommonResult<String> timeout(long millis, int flag) throws InterruptedException {
        return CommonResult.fail("服务超时，请稍后再试");
    }

}

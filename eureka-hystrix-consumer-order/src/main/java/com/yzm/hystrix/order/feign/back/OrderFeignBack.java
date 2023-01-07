package com.yzm.hystrix.order.feign.back;

import com.yzm.commons.api.CommonResult;
import com.yzm.hystrix.order.feign.OrderFeign2;
import org.springframework.stereotype.Component;

@Component
public class OrderFeignBack implements OrderFeign2 {
    @Override
    public CommonResult<String> hello() {
        return CommonResult.fail("服务异常，请稍后再试");
    }

    @Override
    public CommonResult<String> timeout() {
        return CommonResult.fail("服务异常，请稍后再试");
    }
}

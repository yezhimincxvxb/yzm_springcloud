package com.yzm.gateway.oreder.feign.back;

import com.yzm.commons.api.CommonResult;
import com.yzm.gateway.oreder.feign.OrderFeign;
import org.springframework.stereotype.Component;

@Component
public class OrderFeignBack implements OrderFeign {

    @Override
    public CommonResult<String> hello() {
        return CommonResult.fail("服务宕机，请联系管理员");
    }

    @Override
    public CommonResult<String> timeout(long millis) throws InterruptedException {
        return CommonResult.fail("服务超时，请稍后再试");
    }

    @Override
    public CommonResult<String> getById(Integer id) {
        return CommonResult.fail("服务异常，请稍后再试");
    }
}

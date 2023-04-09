package com.yzm.hystrix.order.feign.feign.back;

import com.yzm.commons.api.RespResult;
import com.yzm.hystrix.order.feign.feign.OrderFeign;
import org.springframework.stereotype.Component;

@Component
public class OrderFeignBack implements OrderFeign {
    @Override
    public RespResult<String> hello() {
        return RespResult.fail("服务宕机，请稍后再试");
    }

    @Override
    public RespResult<String> timeout(long millis, int flag) throws InterruptedException {
        return RespResult.fail("服务超时，请稍后再试");
    }

}

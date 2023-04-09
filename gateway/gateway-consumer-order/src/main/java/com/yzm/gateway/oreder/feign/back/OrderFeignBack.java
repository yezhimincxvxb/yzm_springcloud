package com.yzm.gateway.oreder.feign.back;

import com.yzm.commons.api.RespResult;
import com.yzm.gateway.oreder.feign.OrderFeign;
import org.springframework.stereotype.Component;

@Component
public class OrderFeignBack implements OrderFeign {

    @Override
    public RespResult<String> hello() {
        return RespResult.fail("服务宕机，请联系管理员");
    }

    @Override
    public RespResult<String> timeout(long millis) throws InterruptedException {
        return RespResult.fail("服务超时，请稍后再试");
    }

    @Override
    public RespResult<String> getById(Integer id) {
        return RespResult.fail("服务异常，请稍后再试");
    }
}

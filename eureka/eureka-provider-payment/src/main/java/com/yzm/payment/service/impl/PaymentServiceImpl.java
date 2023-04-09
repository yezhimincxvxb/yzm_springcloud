package com.yzm.payment.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.yzm.commons.dto.PaymentDto;
import com.yzm.commons.entity.Payment;
import com.yzm.commons.vo.PaymentVo;
import com.yzm.payment.service.IPaymentService;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 * 服务实现类
 * </p>
 */
@Service
public class PaymentServiceImpl implements IPaymentService {

    private static final Map<Long, Payment> map = new HashMap<>();

    static {
        map.put(1L, Payment.builder().id(1L).username("admin").serialNo(UUID.randomUUID().toString()).build());
        map.put(2L, Payment.builder().id(2L).username("yzm").serialNo(UUID.randomUUID().toString()).build());
        map.put(3L, Payment.builder().id(3L).username("张三").serialNo(UUID.randomUUID().toString()).build());
    }

    @Override
    public PaymentVo getById(Long id) {
        Payment payment = map.get(id);
        PaymentVo pVo = new PaymentVo();
        BeanUtil.copyProperties(payment, pVo);
        return pVo;
    }

    @Override
    public List<PaymentVo> list() {
        Collection<Payment> payments = map.values();
        return BeanUtil.copyToList(payments, PaymentVo.class);
    }

    @Override
    public Boolean save(PaymentDto paymentDto) {
        Payment payment = new Payment();
        BeanUtil.copyProperties(paymentDto, payment);
        long id = map.size() + 1;
        payment.setId(id);
        map.put(id, payment);
        return true;
    }
}

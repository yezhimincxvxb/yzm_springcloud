package com.yzm.payment.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yzm.commons.entity.Payment;
import com.yzm.payment.mapper.PaymentMapper;
import com.yzm.payment.service.IPaymentService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Yzm
 * @since 2023/01/04
 */
@Service
public class PaymentServiceImpl extends ServiceImpl<PaymentMapper, Payment> implements IPaymentService {

}

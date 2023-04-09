package com.yzm.payment.service;

import com.yzm.commons.dto.PaymentDto;
import com.yzm.commons.vo.PaymentVo;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 */
public interface IPaymentService {

    PaymentVo getById(Long id);

    List<PaymentVo> list();

    Boolean save(PaymentDto paymentDto);
}

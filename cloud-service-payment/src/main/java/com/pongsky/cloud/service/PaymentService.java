package com.pongsky.cloud.service;

import com.pongsky.cloud.entity.Payment;
import com.pongsky.cloud.entity.payment.dto.PaymentDto;
import com.pongsky.cloud.exception.InsertException;
import com.pongsky.cloud.exception.ValidationException;
import com.pongsky.cloud.mapper.PaymentMapper;
import com.pongsky.cloud.utils.snowflake.SnowFlakeUtils;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * @author pengsenhao
 * @create 2021-02-13
 */
@Service
@RequiredArgsConstructor
public class PaymentService {

    private final MapperFacade mapperFacade;
    private final PaymentMapper paymentMapper;
    private final SnowFlakeUtils snowFlakeUtils;

    /**
     * 保存支付信息
     *
     * @param userId     用户ID
     * @param paymentDto 支付信息
     */
    @Transactional(rollbackFor = Exception.class)
    public void save(Long userId, PaymentDto paymentDto) {
        Payment payment = mapperFacade.map(paymentDto, Payment.class)
                .setId(snowFlakeUtils.getId())
                .setDataVersion(0L)
                .setCreatedAt(LocalDateTime.now())
                .setUserId(userId);
        InsertException.validation("支付信息保存失败", paymentMapper.save(payment));
    }

    /**
     * 根据序列号和用户ID检验是否存在
     *
     * @param serial 序列号
     * @param userId 用户ID
     */
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public void existsBySerialAndNotUserId(String serial, Long userId) {
        if (StringUtils.isBlank(serial)) {
            return;
        }
        Integer count = paymentMapper.countBySerialAndNotUserId(serial, userId);
        if (count > 0) {
            throw new ValidationException("序列号 " + serial + " 已存在，请更换其他序列号重试");
        }
    }

}

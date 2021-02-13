package com.pongsky.cloud.entity.payment.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author pengsenhao
 * @create 2021-02-10
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class PaymentVo {

    /**
     * 支付ID
     */
    private Long id;

    /**
     * 产品
     */
    private String product;

    /**
     * 序列号
     */
    private String serial;

}

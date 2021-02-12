package com.pongsky.cloud.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 支付表
 * <p>
 * unique - serial
 *
 * @author pengsenhao
 * @create 2021-02-10
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class Payment {

    /**
     * 支付ID
     * <p>
     * bigint(20)、unsigned 、not null
     */
    private Long id;

    /**
     * 序列号
     * <p>
     * varchar(255)、not null
     */
    private String serial;

    /**
     * 创建时间
     * <p>
     * datetime(6)、not null
     */
    private LocalDateTime createdAt;

    /**
     * 修改时间
     * <p>
     * datetime(6)
     */
    private LocalDateTime updatedAt;

}

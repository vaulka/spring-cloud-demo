package com.pongsky.cloud.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

import java.math.BigInteger;
import java.time.LocalDateTime;

/**
 * 支付表
 * <p>
 * unique - id
 *
 * @author pengsenhao
 * @create 2021-02-10
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class Payment {

    /**
     * 唯一标识，bigint(20)、unsigned 、not null
     */
    @Id
    private BigInteger id;

    /**
     * 序列号，varchar(255)、not null
     */
    private String serial;

    /**
     * 创建时间，datetime(6)、not null
     */
    private LocalDateTime createdAt;

    /**
     * 修改时间，datetime(6)
     */
    private LocalDateTime updatedAt;

}

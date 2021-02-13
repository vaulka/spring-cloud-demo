package com.pongsky.cloud.entity.payment.dto;

import com.pongsky.cloud.validator.CreateGroup;
import com.pongsky.cloud.validator.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @author pengsenhao
 * @create 2021-02-13
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class PaymentDto {

    /**
     * 产品
     */
    @NotBlank(groups = {CreateGroup.class})
    @Length(max = 30, groups = {CreateGroup.class, UpdateGroup.class})
    private String product;

    /**
     * 序列号
     */
    @NotBlank(groups = {CreateGroup.class})
    @Length(max = 255, groups = {CreateGroup.class, UpdateGroup.class})
    private String serial;

}

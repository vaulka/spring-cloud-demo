package com.pongsky.cloud.entity.user.dto;

import com.pongsky.cloud.validator.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

/**
 * @author pengsenhao
 * @create 2021-02-12
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class UserDisableDto {

    /**
     * 是否禁用
     */
    @NotNull(groups = {UpdateGroup.class})
    @Range(min = 0, max = 1, groups = {UpdateGroup.class})
    private Integer isDisable;

}

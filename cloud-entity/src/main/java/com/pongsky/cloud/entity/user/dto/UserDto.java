package com.pongsky.cloud.entity.user.dto;

import com.pongsky.cloud.validator.CreateGroup;
import com.pongsky.cloud.validator.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @author pengsenhao
 * @create 2021-02-11
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class UserDto {

    /**
     * 名称
     */
    @NotBlank(groups = {CreateGroup.class})
    @Length(max = 30, groups = {CreateGroup.class, UpdateGroup.class})
    private String name;

    public void setName(String name) {
        this.name = StringUtils.isNotBlank(name) ? name : null;
    }

    /**
     * 手机号
     */
    @NotBlank(groups = {CreateGroup.class})
    @Length(max = 30, groups = {CreateGroup.class, UpdateGroup.class})
    private String phone;

    public void setPhone(String phone) {
        this.phone = StringUtils.isNotBlank(phone) ? phone : null;
    }

}

package com.pongsky.cloud.entity.user.dto;

import com.pongsky.cloud.validator.SearchGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @author pengsenhao
 * @create 2021-02-12
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class RefreshTokenLoginDto {

    /**
     * refreshToken
     */
    @NotBlank(groups = {SearchGroup.class})
    @Length(max = 300, groups = {SearchGroup.class})
    private String refreshToken;

}

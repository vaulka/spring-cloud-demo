package com.pongsky.cloud.utils.jwt.dto;

import com.pongsky.cloud.utils.jwt.enums.AuthRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 鉴权信息
 *
 * @author pengsenhao
 * @create 2021-02-11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class AuthInfo {

    /**
     * ID
     */
    private Long id;

    /**
     * 登录角色
     */
    private AuthRole role;

    /**
     * 公开用户
     */
    public static final AuthInfo PUBLIC_INFO = new AuthInfo(0L, AuthRole.PUBLIC);

}

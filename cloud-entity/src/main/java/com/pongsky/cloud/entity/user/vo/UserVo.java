package com.pongsky.cloud.entity.user.vo;

import com.pongsky.cloud.utils.jwt.enums.AuthRole;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author pengsenhao
 * @create 2021-02-11
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class UserVo {

    /**
     * 用户ID
     */
    private Long id;

    /**
     * 角色
     */
    private AuthRole role;

    /**
     * 用户名
     */
    private String username;

    /**
     * 名称
     */
    private String name;

    /**
     * 手机号
     */
    private String phone;

    /**
     * authorization
     */
    private String authorization;

    /**
     * refreshToken
     */
    private String refreshToken;

}

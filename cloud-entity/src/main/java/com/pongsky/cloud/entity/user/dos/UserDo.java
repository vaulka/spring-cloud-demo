package com.pongsky.cloud.entity.user.dos;

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
public class UserDo {

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
     * 密码
     */
    private String password;

    /**
     * 名称
     */
    private String name;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 是否禁用
     */
    private Integer isDisable;

}

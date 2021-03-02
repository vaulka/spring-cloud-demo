package com.pongsky.cloud.utils.jwt.enums;

/**
 * 鉴权角色
 *
 * @author pengsenhao
 * @create 2021-02-11
 */
public enum AuthRole {

    /**
     * feign 调用
     */
    FEIGN,

    /**
     * 公开
     */
    PUBLIC,

    /**
     * 管理员
     */
    ADMIN,

    /**
     * 用户
     */
    USER;

    /**
     * feign 调用角色
     */
    public static final String FEIGN_ROLE = "FEIGN";

    /**
     * 管理员角色
     */
    public static final String ADMIN_ROLE = "ADMIN";

    /**
     * 用户角色
     */
    public static final String USER_ROLE = "USER";

}

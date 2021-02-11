package com.pongsky.cloud.utils.jwt.enums;

/**
 * @author pengsenhao
 * @create 2021-02-11
 */
public enum AuthRole {

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
     * 管理员角色
     */
    public static final String ADMIN_ROLE = "ADMIN";

    /**
     * 用户角色
     */
    public static final String USER_ROLE = "USER";

}

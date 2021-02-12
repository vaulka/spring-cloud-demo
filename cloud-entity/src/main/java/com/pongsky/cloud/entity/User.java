package com.pongsky.cloud.entity;

import com.pongsky.cloud.utils.jwt.enums.AuthRole;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 用户表
 * <p>
 * unique - role + phone
 *
 * @author pengsenhao
 * @create 2021-02-11
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class User {

    /**
     * 用户ID
     * <p>
     * bigint(20)、unsigned 、not null
     */
    private Long id;

    /**
     * 角色：管理员：ADMIN，用户：USER
     * <p>
     * varchar(10)、not null
     */
    private AuthRole role;

    /**
     * 用户名
     * <p>
     * varchar(20)、not null
     */
    private String username;

    /**
     * 密码
     * <p>
     * varchar(100)、not null
     */
    private String password;

    /**
     * 名称
     * <p>
     * varchar(30)、not null
     */
    private String name;

    /**
     * 手机号
     * <p>
     * varchar(30)、not null
     */
    private String phone;

    /**
     * 是否禁用
     * <p>
     * tinyint(2)、unsigned 、not null
     */
    private Integer isDisable;

    /**
     * 创建时间
     * <p>
     * datetime(6)、not null
     */
    private LocalDateTime createdAt;

    /**
     * 修改时间
     * <p>
     * datetime(6)
     */
    private LocalDateTime updatedAt;

}

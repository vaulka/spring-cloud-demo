-- ----------------------------
-- Database structure for cloud
-- ----------------------------
CREATE DATABASE `cloud` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;


-- ----------------------------
-- Table structure for payment
-- ----------------------------
CREATE TABLE `cloud`.`payment`
(
    `id`         bigint(20) unsigned                     NOT NULL COMMENT '支付ID',
    `serial`     varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '序列号',
    `created_at` datetime(6)                             NOT NULL COMMENT '创建时间',
    `updated_at` datetime DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `unique_serial` (`serial`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;


-- ----------------------------
-- Table structure for user
-- ----------------------------
CREATE TABLE `cloud`.`user`
(
    `id`         bigint(20) unsigned                    NOT NULL COMMENT '用户ID',
    `role`       varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '角色：管理员：ADMIN，用户：USER',
    `name`       varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '名称',
    `phone`      varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '手机号',
    `is_disable` tinyint(2) unsigned                    NOT NULL COMMENT '是否禁用',
    `created_at` datetime(6)                            NOT NULL COMMENT '创建时间',
    `updated_at` datetime(6) DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `unique_role_phone` (`role`, `phone`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;
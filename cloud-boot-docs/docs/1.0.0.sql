-- ----------------------------
-- Database structure for cloud
-- ----------------------------
CREATE DATABASE `cloud` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;


-- ----------------------------
-- Table structure for payment
-- ----------------------------
CREATE TABLE `cloud`.`payment`
(
    `id`           bigint(20) unsigned                     NOT NULL COMMENT '支付ID',
    `product`      varchar(30) COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '产品',
    `serial`       varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '序列号',
    `data_version` bigint(20) unsigned                     NOT NULL COMMENT '数据版本号（乐观锁）',
    `created_at`   datetime(6)                             NOT NULL COMMENT '创建时间',
    `updated_at`   datetime DEFAULT NULL COMMENT '修改时间',
    `user_id`      bigint(20) unsigned                     NOT NULL COMMENT '用户ID',
    PRIMARY KEY (`id`),
    UNIQUE KEY `unique_serial` (`serial`) USING BTREE,
    KEY `index_userId` (`user_id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;


-- ----------------------------
-- Table structure for user
-- ----------------------------
CREATE TABLE `cloud`.`user`
(
    `id`           bigint(20) unsigned                     NOT NULL COMMENT '用户ID',
    `role`         varchar(10) COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '角色：管理员：ADMIN，用户：USER',
    `username`     varchar(20) COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '用户名',
    `password`     varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
    `name`         varchar(30) COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '名称',
    `phone`        varchar(30) COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '手机号',
    `is_disable`   tinyint(2) unsigned                     NOT NULL COMMENT '是否禁用',
    `data_version` bigint(20) unsigned                     NOT NULL COMMENT '数据版本号（乐观锁）',
    `created_at`   datetime(6)                             NOT NULL COMMENT '创建时间',
    `updated_at`   datetime(6) DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `unique_username` (`username`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;


-- ----------------------------
-- Init table data for user
-- ----------------------------
insert `cloud`.`user`(id, role, username, password, name, phone, is_disable, data_version, created_at)
    value (1, 'ADMIN', 'admin', '$2a$10$ps7WiT7B.omuLvsPVBi2aePOitjsprzJ5v.Bgxkb889DGkWjROQaO', '系统管理员',
           '15159845510', 0, 0, '2021-02-10 00:00:00');
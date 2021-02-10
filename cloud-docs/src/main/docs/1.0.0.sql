CREATE TABLE `cloud-payment`.`payment`
(
    `id`         bigint(20) UNSIGNED NOT NULL COMMENT '唯一标识',
    `serial`     varchar(255)        NOT NULL COMMENT '序列号',
    `created_at` datetime(6)         NOT NULL COMMENT '创建时间',
    `updated_at` datetime(6)         NULL COMMENT '修改时间',
    UNIQUE INDEX `unique_id` (`id`) USING BTREE
);
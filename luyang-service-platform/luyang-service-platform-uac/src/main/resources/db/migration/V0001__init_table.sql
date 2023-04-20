create table `uac_tenant`
(
    `id`          BIGINT       NOT NULL COMMENT '主键ID',
    `name`        varchar(128) not null comment '租户名',
    `has_delete`  bit(1)      DEFAULT b'0' COMMENT '是否删除: {[1:删除:true] [0:未删除:false]}',
    `update_time` datetime(3) DEFAULT NULL COMMENT '更新时间',
    `create_time` datetime(3) DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = INNODB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC COMMENT = '租户表';
INSERT INTO `uac_tenant` (`id`, `name`, `has_delete`, `update_time`, `create_time`) VALUES (1648683282704912385, '鲁哈哈TEST', b'0', '2023-04-19 21:41:40.309', '2023-04-19 21:41:40.309');

CREATE TABLE `uac_account`
(
    `id`                     BIGINT       NOT NULL COMMENT '主键ID',
    `user_id`                BIGINT       NOT NULL COMMENT '用户ID',
    `identifier`             VARCHAR(64)  NOT NULL COMMENT '识别码(账号)',
    `credential`             VARCHAR(128) NOT NULL COMMENT '密码凭证',
    `identity_type`          int         DEFAULT 1 COMMENT '识别码类型(账号) {[1:邮箱号] [2:手机号] [3:工号]}',
    `credential_change_date` date        DEFAULT NULL COMMENT '密码凭证上次变更日期',
    `status`                 int         default 1 comment '账号状态{[1:未激活] [2:激活]}',
    `has_delete`             bit(1)      DEFAULT b'0' COMMENT '是否删除: {[1:删除:true] [0:未删除:false]}',
    `update_time`            datetime(3) DEFAULT NULL COMMENT '更新时间',
    `create_time`            datetime(3) DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `uniq_identifier` (`identifier`) USING BTREE COMMENT '识别码唯一索引',
    INDEX `idx_user_id` (`user_id`) USING BTREE COMMENT '用户ID普通索引'
) ENGINE = INNODB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC COMMENT = '账号表';

CREATE TABLE `uac_credential_record`
(
    `id`          BIGINT NOT NULL COMMENT '主键ID',
    `account_id`  BIGINT NOT NULL COMMENT '账号ID',
    `credential`  VARCHAR(128) DEFAULT NULL COMMENT '密码凭证',
    `create_time` datetime(3)  DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `idx_account_id` (`account_id`) USING BTREE COMMENT '账号ID普通索引'
) ENGINE = INNODB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC COMMENT = '密码凭证变更记录表';

CREATE TABLE `uac_user`
(
    `id`          BIGINT       NOT NULL COMMENT '主键ID',
    `name`        VARCHAR(128) NOT NULL COMMENT '姓名',
    `email`       VARCHAR(64)  NOT NULL COMMENT '邮箱',
    `gender`      int          DEFAULT 3 COMMENT '性别 {[1:男] [2:女] [3:保密]}',
    `avatar`      VARCHAR(255) default null comment '头像',
    `remark`      VARCHAR(255) DEFAULT NULL COMMENT '备注',
    `has_delete`  bit(1)       DEFAULT b'0' COMMENT '是否删除: {[1:删除:true] [0:未删除:false]}',
    `update_time` datetime(3)  DEFAULT NULL COMMENT '更新时间',
    `create_time` datetime(3)  DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = INNODB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC COMMENT = '用户表';

CREATE TABLE `uac_dept`
(
    `id`          BIGINT        NOT NULL COMMENT '主键ID',
    `parent_id`   BIGINT        NOT NULL COMMENT '父ID',
    `name`        VARCHAR(128)  NOT NULL COMMENT '部门名称',
    `full_path`   VARCHAR(1024) NOT NULL COMMENT '祖级列表路径',
    `full_name`   VARCHAR(1024) NOT NULL COMMENT '祖级列表名称',
    `sort_num`    INT COMMENT '排序号，值越大越靠前',
    `has_delete`  bit(1)      DEFAULT b'0' COMMENT '是否删除: {[1:删除:true] [0:未删除:false]}',
    `update_time` datetime(3) DEFAULT NULL COMMENT '更新时间',
    `create_time` datetime(3) DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = INNODB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC COMMENT = '部门表';

CREATE TABLE `uac_post`
(
    `id`          BIGINT      NOT NULL COMMENT '主键ID',
    `post_code`   VARCHAR(16) NOT NULL COMMENT '岗位编码',
    `post_name`   VARCHAR(64) NOT NULL COMMENT '岗位名称',
    `sort_num`    INT COMMENT '排序号，值越大越靠前',
    `remark`      VARCHAR(255) DEFAULT NULL COMMENT '备注',
    `has_delete`  bit(1)       DEFAULT b'0' COMMENT '是否删除: {[1:删除:true] [0:未删除:false]}',
    `update_time` datetime(3)  DEFAULT NULL COMMENT '更新时间',
    `create_time` datetime(3)  DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = INNODB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC COMMENT = '岗位表';
INSERT INTO `uac_post` (`id`, `post_code`, `post_name`, `sort_num`, `remark`, `has_delete`, `update_time`,
                        `create_time`)
VALUES (1624071119451258880, 'QA', '测试', 2, NULL, b'0', '2023-02-10 23:41:43.155', '2023-02-10 23:41:43.155');
INSERT INTO `uac_post` (`id`, `post_code`, `post_name`, `sort_num`, `remark`, `has_delete`, `update_time`,
                        `create_time`)
VALUES (1624071119463841792, 'OP', '运维', 7, NULL, b'0', '2023-02-10 23:41:43.155', '2023-02-10 23:41:43.155');
INSERT INTO `uac_post` (`id`, `post_code`, `post_name`, `sort_num`, `remark`, `has_delete`, `update_time`,
                        `create_time`)
VALUES (1624071119463841793, 'RD', '研发', 3, NULL, b'0', '2023-02-10 23:41:43.155', '2023-02-10 23:41:43.155');
INSERT INTO `uac_post` (`id`, `post_code`, `post_name`, `sort_num`, `remark`, `has_delete`, `update_time`,
                        `create_time`)
VALUES (1624071119468036096, 'DBA', '数据库管理员', 1, NULL, b'0', '2023-02-10 23:41:43.155', '2023-02-10 23:41:43.155');
INSERT INTO `uac_post` (`id`, `post_code`, `post_name`, `sort_num`, `remark`, `has_delete`, `update_time`,
                        `create_time`)
VALUES (1624071119468036097, 'PM', '产品经理', 9, NULL, b'0', '2023-02-10 23:41:43.155', '2023-02-10 23:41:43.155');
INSERT INTO `uac_post` (`id`, `post_code`, `post_name`, `sort_num`, `remark`, `has_delete`, `update_time`,
                        `create_time`)
VALUES (1624071119468036098, 'FE', '前端开发', 5, NULL, b'0', '2023-02-10 23:41:43.155', '2023-02-10 23:41:43.155');


CREATE TABLE `uac_user_dept`
(
    `id`          BIGINT NOT NULL COMMENT '主键ID',
    `user_id`     BIGINT NOT NULL COMMENT '用户ID',
    `dept_id`     BIGINT NOT NULL COMMENT '部门ID',
    `has_delete`  bit(1)      DEFAULT b'0' COMMENT '是否删除: {[1:删除:true] [0:未删除:false]}',
    `update_time` datetime(3) DEFAULT NULL COMMENT '更新时间',
    `create_time` datetime(3) DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = INNODB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC COMMENT = '用户与部门关联表';

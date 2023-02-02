CREATE TABLE `uac_account`
(
    `id`                          VARCHAR(64)  NOT NULL COMMENT '主键ID',
    `user_id`                     VARCHAR(64)  NOT NULL COMMENT '用户ID',
    `identifier`                  VARCHAR(64)  NOT NULL COMMENT '识别码(账号)',
    `credential`                  VARCHAR(128) NOT NULL COMMENT '密码凭证',
    `identity_type`               CHAR(2) DEFAULT '01' COMMENT '识别码类型 {[01:工号] [02:邮箱] [03:手机号]}',
    `source`                      CHAR(2) DEFAULT '01' COMMENT '账号来源 {[01:内部系统]}',
    `status`                      CHAR(2) DEFAULT '01' COMMENT '账号状态 {[01:激活] [02:未激活] [03:停用]}',
    `has_delete`                  CHAR(1) DEFAULT 'N' COMMENT '已删除: {[删除:Y] [未删除:N]}',
    `credential_last_change_date` date    DEFAULT NULL COMMENT '密码凭证上次变更日期',
    `update_time`                 datetime(3) DEFAULT NULL COMMENT '更新时间',
    `create_time`                 datetime(3) DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `uniq_identifier` (`identifier`) USING BTREE COMMENT '识别码唯一索引',
    INDEX                         `idx_user_id` (`user_id`) USING BTREE COMMENT '用户ID普通索引'
) ENGINE = INNODB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC COMMENT = '账号表';

CREATE TABLE `uac_credential_record`
(
    `id`          VARCHAR(64) NOT NULL COMMENT '主键ID',
    `account_id`  VARCHAR(32) NOT NULL COMMENT '账号ID',
    `credential`  VARCHAR(128) DEFAULT NULL COMMENT '密码凭证',
    `create_time` datetime(3) DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX         `idx_account_id` (`account_id`) USING BTREE COMMENT '账号ID普通索引'
) ENGINE = INNODB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC COMMENT = '密码凭证变更记录表';

CREATE TABLE `uac_user`
(
    `id`             VARCHAR(64)  NOT NULL COMMENT '主键ID',
    `job_num`        VARCHAR(16)  NOT NULL COMMENT '工号',
    `name`           VARCHAR(128) NOT NULL COMMENT '姓名',
    `email`          VARCHAR(64)  NOT NULL COMMENT '邮箱',
    `leader_user_id` VARCHAR(64)  DEFAULT NULL COMMENT '直接上级ID',
    `user_type`      CHAR(2)      DEFAULT '01' COMMENT '用户类型 {[01:正式员工] [02:实习生] [03:外协]}',
    `country`        CHAR(2)      DEFAULT NULL COMMENT '就职国家',
    `city`           VARCHAR(32)  DEFAULT NULL COMMENT '就职城市',
    `hire_date`      date         DEFAULT NULL COMMENT '雇佣日期',
    `rank`           VARCHAR(12)  DEFAULT NULL COMMENT '职级',
    `status`         CHAR(2)      DEFAULT '01' COMMENT '员工状态 {[01:在职] [02:离职]}',
    `gender`         CHAR(2)      DEFAULT '03' COMMENT '性别 {[01:男] [02:女] [03:保密]}',
    `remark`         VARCHAR(255) DEFAULT NULL COMMENT '备注',
    `has_delete`     CHAR(1)      DEFAULT 'N' COMMENT '已删除: {[删除:Y] [未删除:N]}',
    `update_time`    datetime(3) DEFAULT NULL COMMENT '更新时间',
    `create_time`    datetime(3) DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = INNODB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC COMMENT = '用户表';

CREATE TABLE `uac_department`
(
    `id`          VARCHAR(64)   NOT NULL COMMENT '主键ID',
    `parent_id`   VARCHAR(64)   NOT NULL COMMENT '父ID',
    `name`        VARCHAR(128)  NOT NULL COMMENT '部门名称',
    `full_path`   VARCHAR(1024) NOT NULL COMMENT '祖级列表路径',
    `full_name`   VARCHAR(1024) NOT NULL COMMENT '祖级列表名称',
    `sort_num`    INT COMMENT '排序号，值越大越靠前',
    `has_delete`  CHAR(1) DEFAULT 'N' COMMENT '已删除: {[删除:Y] [未删除:N]}',
    `update_time` datetime(3) DEFAULT NULL COMMENT '更新时间',
    `create_time` datetime(3) DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = INNODB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC COMMENT = '部门表';

CREATE TABLE `uac_post`
(
    `id`          VARCHAR(64)  NOT NULL COMMENT '主键ID',
    `code`        VARCHAR(64)  NOT NULL COMMENT '岗位编码',
    `name`        VARCHAR(128) NOT NULL COMMENT '岗位名称',
    `sort_num`    INT COMMENT '排序号，值越大越靠前',
    `remark`      VARCHAR(255) DEFAULT NULL COMMENT '备注',
    `has_delete`  CHAR(1)      DEFAULT 'N' COMMENT '已删除: {[删除:Y] [未删除:N]}',
    `update_time` datetime ( 3 ) DEFAULT NULL COMMENT '更新时间',
    `create_time` datetime ( 3 ) DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC COMMENT = '岗位表';

CREATE TABLE `uac_user_department`
(
    `id`            VARCHAR(32) NOT NULL COMMENT '主键ID',
    `user_id`       VARCHAR(32) NOT NULL COMMENT '用户ID',
    `department_id` VARCHAR(32) NOT NULL COMMENT '部门ID',
    `has_delete`    CHAR(1) DEFAULT 'N' COMMENT '已删除: {[删除:Y] [未删除:N]}',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = INNODB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC COMMENT = '用户与部门关联表';

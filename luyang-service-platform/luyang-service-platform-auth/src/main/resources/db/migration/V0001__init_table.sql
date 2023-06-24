CREATE TABLE auth_registered_client
(
    client_id                     VARCHAR(32)                             NOT NULL COMMENT '客户端ID',
    client_name                   VARCHAR(64)                             NOT NULL COMMENT '客户端名称',
    client_secret                 VARCHAR(128)                            NOT NULL COMMENT '客户端密钥',
    authorization_grant_types     VARCHAR(256)                            NOT NULL COMMENT '授权认证类型',
    scopes                        VARCHAR(256)                            NOT NULL COMMENT '客户端授权范围，多个以英文逗号分割',
    client_authentication_methods VARCHAR(256)                            NOT NULL COMMENT '客户端认证方式',
    redirect_uris                 VARCHAR(256)  DEFAULT NULL COMMENT '回调地址',
    client_id_issued_at           TIMESTAMP     DEFAULT CURRENT_TIMESTAMP NOT NULL,
    client_secret_expires_at      TIMESTAMP     DEFAULT NULL,
    post_logout_redirect_uris     VARCHAR(1000) DEFAULT NULL,
    client_settings               VARCHAR(2000)                           NOT NULL,
    token_settings                VARCHAR(2000)                           NOT NULL,
    PRIMARY KEY (client_id)
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC COMMENT = '客户端表';
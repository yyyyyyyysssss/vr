/*
 Navicat Premium Data Transfer

 Source Server         : home
 Source Server Type    : MySQL
 Source Server Version : 80033
 Source Host           : 192.168.31.180:3306
 Source Schema         : vehicle_rental

 Target Server Type    : MySQL
 Target Server Version : 80033
 File Encoding         : 65001

 Date: 30/11/2025 20:58:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for oauth2_authorization
-- ----------------------------
DROP TABLE IF EXISTS `oauth2_authorization`;
CREATE TABLE `oauth2_authorization`  (
                                         `id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                                         `registered_client_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                                         `principal_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                                         `authorization_grant_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                                         `authorized_scopes` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                                         `attributes` blob NULL,
                                         `state` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                                         `authorization_code_value` blob NULL,
                                         `authorization_code_issued_at` timestamp(0) NULL DEFAULT NULL,
                                         `authorization_code_expires_at` timestamp(0) NULL DEFAULT NULL,
                                         `authorization_code_metadata` blob NULL,
                                         `access_token_value` blob NULL,
                                         `access_token_issued_at` timestamp(0) NULL DEFAULT NULL,
                                         `access_token_expires_at` timestamp(0) NULL DEFAULT NULL,
                                         `access_token_metadata` blob NULL,
                                         `access_token_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                                         `access_token_scopes` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                                         `oidc_id_token_value` blob NULL,
                                         `oidc_id_token_issued_at` timestamp(0) NULL DEFAULT NULL,
                                         `oidc_id_token_expires_at` timestamp(0) NULL DEFAULT NULL,
                                         `oidc_id_token_metadata` blob NULL,
                                         `refresh_token_value` blob NULL,
                                         `refresh_token_issued_at` timestamp(0) NULL DEFAULT NULL,
                                         `refresh_token_expires_at` timestamp(0) NULL DEFAULT NULL,
                                         `refresh_token_metadata` blob NULL,
                                         `user_code_value` blob NULL,
                                         `user_code_issued_at` timestamp(0) NULL DEFAULT NULL,
                                         `user_code_expires_at` timestamp(0) NULL DEFAULT NULL,
                                         `user_code_metadata` blob NULL,
                                         `device_code_value` blob NULL,
                                         `device_code_issued_at` timestamp(0) NULL DEFAULT NULL,
                                         `device_code_expires_at` timestamp(0) NULL DEFAULT NULL,
                                         `device_code_metadata` blob NULL,
                                         PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for oauth2_authorization_consent
-- ----------------------------
DROP TABLE IF EXISTS `oauth2_authorization_consent`;
CREATE TABLE `oauth2_authorization_consent`  (
                                                 `registered_client_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                                                 `principal_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                                                 `authorities` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                                                 PRIMARY KEY (`registered_client_id`, `principal_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for oauth2_registered_client
-- ----------------------------
DROP TABLE IF EXISTS `oauth2_registered_client`;
CREATE TABLE `oauth2_registered_client`  (
                                             `id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                                             `client_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                                             `client_id_issued_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
                                             `client_secret` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                                             `client_secret_expires_at` timestamp(0) NULL DEFAULT NULL,
                                             `client_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                                             `client_authentication_methods` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                                             `authorization_grant_types` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                                             `redirect_uris` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                                             `post_logout_redirect_uris` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                                             `scopes` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                                             `client_settings` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                                             `token_settings` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                                             PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for one_time_tokens
-- ----------------------------
DROP TABLE IF EXISTS `one_time_tokens`;
CREATE TABLE `one_time_tokens`  (
                                    `token_value` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                                    `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                                    `expires_at` timestamp(0) NOT NULL,
                                    PRIMARY KEY (`token_value`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for tmp
-- ----------------------------
DROP TABLE IF EXISTS `tmp`;
CREATE TABLE `tmp`  (
                        `id` bigint NOT NULL,
                        PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for vr_authority
-- ----------------------------
DROP TABLE IF EXISTS `vr_authority`;
CREATE TABLE `vr_authority`  (
                                 `id` bigint NOT NULL,
                                 `parent_id` bigint NULL DEFAULT NULL COMMENT '父节点id',
                                 `root_id` bigint NULL DEFAULT NULL COMMENT '根节点id',
                                 `code` varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限编码',
                                 `name` varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限名称',
                                 `type` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限类型 MENU: 菜单 BUTTON: 按钮 ROOT:所有权限 BASE:不需要授权即可访问',
                                 `route_path` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '对应前端路由',
                                 `urls` json NULL COMMENT '该权限控制的url',
                                 `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图标',
                                 `sort` int NULL DEFAULT NULL COMMENT '排序',
                                 `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
                                 `creator_id` bigint NULL DEFAULT NULL COMMENT '创建人id',
                                 `creator_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人名称',
                                 `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
                                 `updater_id` bigint NULL DEFAULT NULL COMMENT '修改人id',
                                 `updater_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人名称',
                                 PRIMARY KEY (`id`) USING BTREE,
                                 UNIQUE INDEX `code`(`code`) USING BTREE,
                                 INDEX `tree_idx`(`parent_id`, `root_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for vr_files
-- ----------------------------
DROP TABLE IF EXISTS `vr_files`;
CREATE TABLE `vr_files`  (
                             `id` bigint NOT NULL,
                             `upload_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '上传id',
                             `file_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件原始名称',
                             `file_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件类型',
                             `total_size` double NULL DEFAULT NULL COMMENT '文件总大小',
                             `total_chunk` int NULL DEFAULT NULL,
                             `chunk_size` int NULL DEFAULT NULL,
                             `uploaded_chunk_count` int NULL DEFAULT NULL,
                             `etag` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'minio文件标识',
                             `access_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '访问url',
                             `original_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '原始访问url',
                             `status` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'PENDING, COMPLETED, FAILED',
                             `storage_type` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                             `create_time` datetime(0) NULL DEFAULT NULL,
                             `creator_id` bigint NULL DEFAULT NULL COMMENT '创建人id',
                             `creator_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人名称',
                             `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
                             `updater_id` bigint NULL DEFAULT NULL COMMENT '修改人id',
                             `updater_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人名称',
                             PRIMARY KEY (`id`) USING BTREE,
                             UNIQUE INDEX `upload_id_idx`(`upload_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for vr_role
-- ----------------------------
DROP TABLE IF EXISTS `vr_role`;
CREATE TABLE `vr_role`  (
                            `id` bigint NOT NULL,
                            `code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色编码',
                            `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
                            `enabled` tinyint(1) NULL DEFAULT NULL COMMENT '是否启用（1=启用，0=禁用）',
                            `type` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'NORMAL(普通角色) SUPER_ADMIN(超级管理员)',
                            `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
                            `creator_id` bigint NULL DEFAULT NULL COMMENT '创建人id',
                            `creator_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人名称',
                            `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
                            `updater_id` bigint NULL DEFAULT NULL COMMENT '修改人id',
                            `updater_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人名称',
                            PRIMARY KEY (`id`) USING BTREE,
                            UNIQUE INDEX `code_idx`(`code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for vr_role_authority
-- ----------------------------
DROP TABLE IF EXISTS `vr_role_authority`;
CREATE TABLE `vr_role_authority`  (
                                      `id` bigint NOT NULL,
                                      `role_id` bigint NOT NULL COMMENT '关联角色id',
                                      `authority_id` bigint NOT NULL COMMENT '关联权限id',
                                      PRIMARY KEY (`id`) USING BTREE,
                                      INDEX `default_idx`(`role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for vr_user
-- ----------------------------
DROP TABLE IF EXISTS `vr_user`;
CREATE TABLE `vr_user`  (
                            `id` bigint NOT NULL,
                            `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '登录账号',
                            `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
                            `full_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户全名',
                            `email` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
                            `phone` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号',
                            `enabled` tinyint(1) NULL DEFAULT NULL COMMENT '1 启用 0 禁用',
                            `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
                            `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
                            `creator_id` bigint NULL DEFAULT NULL COMMENT '创建人id',
                            `creator_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人名称',
                            `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
                            `updater_id` bigint NULL DEFAULT NULL COMMENT '修改人id',
                            `updater_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人名称',
                            PRIMARY KEY (`id`) USING BTREE,
                            UNIQUE INDEX `username_idx`(`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for vr_user_2fa
-- ----------------------------
DROP TABLE IF EXISTS `vr_user_2fa`;
CREATE TABLE `vr_user_2fa`  (
                                `id` bigint NOT NULL,
                                `user_id` bigint NOT NULL COMMENT '用户id',
                                `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户账号',
                                `type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '类型 TOTP、SMS',
                                `secret` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密钥',
                                `enabled` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否启用',
                                PRIMARY KEY (`id`) USING BTREE,
                                INDEX `user_id_idx`(`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for vr_user_role
-- ----------------------------
DROP TABLE IF EXISTS `vr_user_role`;
CREATE TABLE `vr_user_role`  (
                                 `id` bigint NOT NULL,
                                 `user_id` bigint NOT NULL COMMENT '关联用户id',
                                 `role_id` bigint NOT NULL COMMENT '关联角色id',
                                 PRIMARY KEY (`id`) USING BTREE,
                                 INDEX `user_id_idx`(`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;

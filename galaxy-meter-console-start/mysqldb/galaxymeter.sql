SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` varchar(128) NOT NULL COMMENT '主键id',
  `login_id` varchar(64) NOT NULL COMMENT '用户名',
  `user_name` varchar(64) NOT NULL COMMENT '用户姓名',
  `password` varchar(128) NOT NULL COMMENT '用户密码',
  `phone` varchar(16) DEFAULT NULL COMMENT '电话',
  `email` varchar(64) DEFAULT NULL COMMENT '邮箱',
  `enabled` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否禁用（0未开启，1开启）',
  `admin_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否系统管理员（0否，1是）',
  `login_ip` varchar(50) DEFAULT '' COMMENT '最后登录IP',
  `login_date` datetime DEFAULT NULL COMMENT '最后登录时间',
  `created_by` varchar(128) DEFAULT NULL COMMENT '创建者',
  `updated_by` varchar(128) DEFAULT NULL COMMENT '更新者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `sys_user_ukey_login_id` (`login_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户信息表';


-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` varchar(128) NOT NULL COMMENT '主键id',
  `user_id` varchar(128) NOT NULL COMMENT '用户ID',
  `role_id` varchar(128) NOT NULL COMMENT '角色ID',
  `created_by` varchar(128) DEFAULT NULL COMMENT '创建者',
  `updated_by` varchar(128) DEFAULT NULL COMMENT '更新者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户角色关联表';


-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` varchar(128) NOT NULL COMMENT '主键id',
  `role_id` varchar(128) NOT NULL COMMENT '角色ID',
  `menu_id` varchar(128) NOT NULL COMMENT '菜单ID',
  `created_by` varchar(128) DEFAULT NULL COMMENT '创建者',
  `updated_by` varchar(128) DEFAULT NULL COMMENT '更新者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='角色菜单关联表';


-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` varchar(128) NOT NULL COMMENT '主键id',
  `menu_code` varchar(128) NOT NULL COMMENT '菜单代码',
  `name` varchar(64) NOT NULL COMMENT '菜单名称',
  `parent_id` varchar(128) NOT NULL COMMENT '父菜单ID',
  `sort` tinyint(4) NOT NULL DEFAULT '0' COMMENT '排序',
  `url` varchar(256) NOT NULL COMMENT 'URL',
  `type` tinyint(4) NOT NULL DEFAULT '1' COMMENT '菜单类型（1目录 2菜单 3按钮）',
  `target` varchar(16) NOT NULL DEFAULT '' COMMENT '打开方式（menuItem页签 menuBlank新窗口）',
  `perms` varchar(256) DEFAULT '' COMMENT '权限标识',
  `icon` varchar(256) NOT NULL DEFAULT '' COMMENT '菜单图标',
  `component` varchar(128) DEFAULT NULL COMMENT '组件',
  `enabled` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否禁用（0未开启，1开启）',
  `created_by` varchar(128) DEFAULT NULL COMMENT '创建者',
  `updated_by` varchar(128) DEFAULT NULL COMMENT '更新者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='菜单信息表';


-- ----------------------------
-- Table structure for sys_user_app_env
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_app_env`;
CREATE TABLE `sys_user_app_env` (
  `id` varchar(128) NOT NULL COMMENT '主键id',
  `user_id` varchar(128) NOT NULL COMMENT '用户id',
  `app_id` varchar(128) NOT NULL COMMENT '应用id',
  `env_id` varchar(128) NOT NULL COMMENT '环境id',
  `created_by` varchar(128) DEFAULT NULL COMMENT '创建者',
  `updated_by` varchar(128) DEFAULT NULL COMMENT '更新者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户应用环境权限表';


-- ----------------------------
-- Table structure for app_info
-- ----------------------------
DROP TABLE IF EXISTS `app_info`;
CREATE TABLE `app_info` (
  `id` varchar(128) NOT NULL COMMENT '主键id',
  `app_code` varchar(128) NOT NULL COMMENT '应用代码',
  `name` varchar(64) NOT NULL COMMENT '应用名称',
  `user_id` varchar(128) COMMENT '用户ID',
  `manager` varchar(128) COMMENT '负责人',
  `description` varchar(1024) COMMENT '描述',
  `created_by` varchar(128) DEFAULT NULL COMMENT '创建者',
  `updated_by` varchar(128) DEFAULT NULL COMMENT '更新者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `app_info_ukey` (`app_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='应用信息表';


-- ----------------------------
-- Table structure for app_env_info
-- ----------------------------
DROP TABLE IF EXISTS `app_env_info`;
CREATE TABLE `app_env_info` (
  `id` varchar(128) NOT NULL COMMENT '主键id',
  `env_code` varchar(128) NOT NULL COMMENT '环境代码',
  `name` varchar(64) NOT NULL COMMENT '环境名称',
  `description` varchar(1024) COMMENT '描述',
  `created_by` varchar(128) DEFAULT NULL COMMENT '创建者',
  `updated_by` varchar(128) DEFAULT NULL COMMENT '更新者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `app_info_env_ukey` (`env_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='环境信息表';


-- ----------------------------
-- Table structure for sys_oper_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_oper_log`;
CREATE TABLE `sys_oper_log` (
  `id` varchar(128) NOT NULL COMMENT '主键id',
  `business_type` varchar(128) NOT NULL COMMENT '业务类型',
  `oper_type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '操作类型（0其它 1新增 2修改 3删除）',
  `method` varchar(128) NOT NULL DEFAULT '' COMMENT '方法名称',
  `request_method` varchar(16) NOT NULL DEFAULT '' COMMENT '请求方式',
  `oper_id` varchar(128) NOT NULL COMMENT '操作用户ID',
  `oper_name` varchar(64) COMMENT '操作用户姓名',
  `oper_url` varchar(256) NOT NULL COMMENT '操作URL',
  `oper_ip` varchar(64) NOT NULL COMMENT '主机IP',
  `oper_param` text COMMENT '请求参数',
  `json_result` text COMMENT '返回参数',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '操作状态（0正常 1异常）',
  `result_code` varchar(16) NOT NULL DEFAULT '' COMMENT '返回码',
  `result_msg` text COMMENT '返回信息',
  `oper_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='操作日志表';


-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
  `id` varchar(128) NOT NULL COMMENT '主键id',
  `name` varchar(128) NOT NULL COMMENT '参数名称',
  `config_key` varchar(64) NOT NULL COMMENT '参数键名',
  `config_value` varchar(512) NOT NULL COMMENT '参数键值',
  `enabled` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否禁用（0未开启，1开启）',
  `created_by` varchar(128) DEFAULT NULL COMMENT '创建者',
  `updated_by` varchar(128) DEFAULT NULL COMMENT '更新者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `sys_config_ukey` (`config_key`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='系统配置表';


-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type` (
  `id` varchar(128) NOT NULL COMMENT '主键id',
  `name` varchar(128) NOT NULL COMMENT '字典名称',
  `type` varchar(64) NOT NULL COMMENT '字典类型',
  `system_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否系统级（0否,1是)',
  `enabled` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否禁用（0未开启，1开启）',
  `created_by` varchar(128) DEFAULT NULL COMMENT '创建者',
  `updated_by` varchar(128) DEFAULT NULL COMMENT '更新者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `sys_dict_type_ukey` (`type`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='字典类型表';


-- ----------------------------
-- Table structure for sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data` (
    `id` varchar(128) NOT NULL  COMMENT '主键id',
    `type_id` varchar(128) NOT NULL COMMENT '字典类型:',
    `default_flag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否默认（0否，1是）',
    `dict_code` varchar(62) NOT NULL COMMENT '字典code',
    `dict_name` varchar(128) NOT NULL COMMENT '字典name',
    `sort` int(4) NOT NULL COMMENT '排序',
    `system_flag` int(1) NOT NULL DEFAULT '0' COMMENT '是否系统级,否(0),是(1)',
    `enabled` tinyint(4) NOT NULL DEFAULT '1' COMMENT '是否开启（默认未开启：0未开启，1开启）',
    `created_by` varchar(100) DEFAULT NULL COMMENT '创建者',
    `updated_by` varchar(100) DEFAULT NULL COMMENT '更新者',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    `remark` varchar(500) DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `sys_dict_data_ukey` (`type_id`,`dict_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='字典数据表';


-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` varchar(128) NOT NULL COMMENT '主键id',
  `name` varchar(64) NOT NULL COMMENT '角色名称',
  `type` tinyint(4) NOT NULL DEFAULT '1' COMMENT '角色类型（0系统默认，1自定义）',
  `enabled` tinyint(4) NOT NULL DEFAULT '1' COMMENT '是否禁用（0未开启，1开启）',
  `created_by` varchar(128) DEFAULT NULL COMMENT '创建者',
  `updated_by` varchar(128) DEFAULT NULL COMMENT '更新者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='角色信息表';
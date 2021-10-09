package com.csii.meter.console.utils.redis;

/**
 * redis key
 *
 * @author guoyu
 * @version 1.0
 * @date 2021/9/1 4:10 下午
 */
public interface RedisConstant {
    //分布式锁用lock:key前缀，普通锁用key区分


    //新增-服务器ip
    String SERVER_ADD_HOST_LOCK_KEY = "lock:key:server:add:host";

    //更新-服务器ip
    String SERVER_UPDATE_HOST_LOCK_KEY = "lock:key:server:update:host";

    //新增-项目管理名称
    String PROJECT_ADD_NAME_LOCK_KEY = "lock:key:project:add:name";

    //更新-项目管理名称
    String PROJECT_UPDATE_NAME_LOCK_KEY = "lock:key:project:update:name";

    //新增-节点管理code
    String NODE_ADD_CODE_LOCK_KEY = "lock:key:node:add:code";

    //更新-节点管理code
    String NODE_UPDATE_CODE_LOCK_KEY = "lock:key:node:update:code";

    //新增-appCode
    String APP_ADD_CODE_LOCK_KEY = "lock:key:app:add:code";

    //更新-appCode
    String APP_UPDATE_CODE_LOCK_KEY = "lock:key:app:update:code";

    //新增-envCode
    String ENV_ADD_CODE_LOCK_KEY = "lock:key:env:add:code";

    //更新-envCode
    String ENV_UPDATE_CODE_LOCK_KEY = "lock:key:env:update:code";

    //新增-代码管理名称
    String CODE_ADD_NAME_LOCK_KEY = "lock:key:code:add:name";

    //新增-maven仓库名称
    String MAVEN_ADD_NAME_LOCK_KEY = "lock:key:maven:add:name";

    //新增-配置管理ID
    String CONFIG_ADD_ID_LOCK_KEY = "lock:key:config:add:id";

    //更新-配置管理ID
    String CONFIG_UPDATE_ID_LOCK_KEY = "lock:key:config:update:id";

    //新增-错误码ID
    String MESSAGE_ADD_ID_LOCK_KEY = "lock:key:message:add:id";

    //更新-错误码ID
    String MESSAGE_UPDATE_ID_LOCK_KEY = "lock:key:message:update:id";

    //新增-mapping映射ID
    String MAPPING_ADD_ID_LOCK_KEY = "lock:key:mapping:add:id";

    //更新-mapping映射ID
    String MAPPING_UPDATE_ID_LOCK_KEY = "lock:key:mapping:update:id";

    //新增-流程配置ID
    String FLOW_ADD_ID_LOCK_KEY = "lock:key:flow:add:id";

    //更新-流程配置ID
    String FLOW_UPDATE_ID_LOCK_KEY = "lock:key:flow:update:id";

    //新增-场景编排ID
    String SCENE_ADD_ID_LOCK_KEY = "lock:key:scene:add:id";

    //更新-场景编排ID
    String SCENE_UPDATE_ID_LOCK_KEY = "lock:key:scene:update:id";

    //新增-styleID
    String STYLE_ADD_ID_LOCK_KEY = "lock:key:style:add:id";

    //更新-styleID
    String STYLE_UPDATE_ID_LOCK_KEY = "lock:key:style:update:id";

    //新增-交易ID
    String TRAN_ADD_ID_LOCK_KEY = "lock:key:tran:add:id";

    //更新-交易ID
    String TRAN_UPDATE_ID_LOCK_KEY = "lock:key:tran:update:id";
}

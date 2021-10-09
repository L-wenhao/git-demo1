package com.csii.meter.console.constants;

/**
 * @author liangjianan
 * @Description 数据字典Code枚举
 * @Date 2021/6/10 16:37
 */
public interface DictDataConstant {

    //代码管理：代码来源方式
    String CODE_SOURCE = "code_source";
    //git方式
    String CODE_SOURCE_0 = "0";
    //源码压缩包方式
    String CODE_SOURCE_1 = "1";
    //部署jar包
    String CODE_SOURCE_2 = "2";

    //代码编译状态：0-未编译；1-已编译;2-编译失败
    String PACKAGE_STATUS = "package_status";
    String PACKAGE_STATUS_0 = "0";
    String PACKAGE_STATUS_1 = "1";
    String PACKAGE_STATUS_2 = "2";

    //发布状态：0-未发布；1-已发布；2-变更(变更状态由变更表是否存在对应业务数据判断)
    String PUBLISH_STATUS = "publish_status";
    String PUBLISH_STATUS_0 = "0";
    String PUBLISH_STATUS_1 = "1";
    String PUBLISH_STATUS_2 = "2";

    /**
     * 消息标识
     */
    public static final String COMMAND_ACTION = "action";

    //加载
    public static final String LOAD = "load";
    //禁用
    public static final String DISABLE = "disable";

    //配置业务类型：0-交易，1-流程，2-style,3-mapping,4-错误码，5-模板，6-试图，7-api数据：
    String CONFIG_BIZ_TYPE = "config_biz_type";
    String CONFIG_BIZ_TYPE_0 = "0";
    String CONFIG_BIZ_TYPE_1 = "1";
    String CONFIG_BIZ_TYPE_2 = "2";
    String CONFIG_BIZ_TYPE_3 = "3";
    String CONFIG_BIZ_TYPE_4 = "4";
    String CONFIG_BIZ_TYPE_5 = "5";
    String CONFIG_BIZ_TYPE_6 = "6";
    String CONFIG_BIZ_TYPE_7 = "7";

    //数据状态：0-不可用；1-可用
    String DATA_STATUS = "DATA_STATUS";
    String DATA_STATUS_0 = "0";
    String DATA_STATUS_1 = "1";

    //开发管理，发布/取消发布操作：业务数据处理标识：able-未发布数据，disable-取消发布，change-变更未发布
    String DEV_ABLE = "dev_able";
    String DEV_DISABLE = "dev_disable";
    String DEV_CHANGE = "dev_change";

    //节点状态：0-未运行；1-运行中; 2-启动失败
    String NODE_STATUS = "NODE_STATUS";
    String NODE_STATUS_0 = "0";
    String NODE_STATUS_1 = "1";
    String NODE_STATUS_2 = "2";

}

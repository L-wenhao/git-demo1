package com.csii.meter.console.constants;

/**
 * 命令常量定义
 *
 * @author 陈新萍
 */
public class CommandConstant {
    /**
     * 消息订阅加载流程主题
     */
    public static final String TOPIC_LOAD_FLOW = "TOPIC_LOAD_FLOW";
    /**
     * 消息订阅加载交易主题
     */
    public static final String TOPIC_LOAD_TRAN = "TOPIC_LOAD_TRAN";
    /**
     * 消息订阅加载style校验主题
     */
    public static final String TOPIC_LOAD_STYLE = "TOPIC_LOAD_STYLE";
    /**
     * 消息订阅加载mapping主题
     */
    public static final String TOPIC_LOAD_MAPPING = "TOPIC_LOAD_MAPPING";
    /**
     * 消息订阅加载校验错误码主题
     */
    public static final String TOPIC_LOAD_ERROR_CODE = "TOPIC_LOAD_ERROR_CODE";
    /**
     * 消息订阅扫描包主题
     */
    public static final String TOPIC_SCAN_JAR = "TOPIC_SCAN_JAR";
    /**
     * 消息订阅API包主题
     */
    public static final String TOPIC_LOAD_JAR = "TOPIC_LOAD_JAR";
}

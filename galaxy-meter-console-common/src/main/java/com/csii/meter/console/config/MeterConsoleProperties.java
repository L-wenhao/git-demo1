package com.csii.meter.console.config;


import com.csii.meter.console.utils.PathUtils;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 控制台配置类
 */
@Data
@ConfigurationProperties(prefix = "console", ignoreInvalidFields = true)
public class MeterConsoleProperties {
    /**
     * 文件存储根目录
     */
    private static String basePath = "/";
    /**
     * 临时目录
     */
    private static String tempDir = "/temp";
    /**
     * 存储目录
     */
    private static String localStore = "/store";
    /**
     * api参数解析深度，默认5层
     */
    private static int apiParamParseDepth = 5;
    /**
     * 最大上传文件大小
     */
    private static long uploadMaxSize = 200;

    public static String getTempPath(){
        return PathUtils.normalize(basePath + PathUtils.UNIX_SEPARATOR_S + tempDir);
    }

    public static String getStorePath(){
        return PathUtils.normalize(basePath + PathUtils.UNIX_SEPARATOR_S + tempDir);
    }

    public static long getUploadMaxSize(){
        return uploadMaxSize;
    }

}

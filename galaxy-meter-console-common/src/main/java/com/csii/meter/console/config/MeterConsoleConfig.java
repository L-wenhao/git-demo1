package com.csii.meter.console.config;

import com.csii.meter.console.exception.MeterConsoleException;
import com.csii.meter.console.utils.ConvertUtils;
import com.csii.meter.console.utils.PathUtils;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class MeterConsoleConfig {
    private static ConcurrentMap configMap = new ConcurrentHashMap();

    protected static void put(String key, String value){
        configMap.put(key, value);
    }

    protected static Object remove(String key){
        return configMap.remove(key);
    }

    public static Object get(String key, Object defaultValue){
        Object value = configMap.get(key);
        if(Objects.isNull(value)){
            return defaultValue;
        }
        return value;
    }

    public static Object get(String key){
        Object value = configMap.get(key);
        if(Objects.isNull(value)){
            throw new MeterConsoleException(String.format("未找到参数[%s]对应的值,请到系统参数管理中添加", key));
        }
        return value;
    }

    public static String getString(String key){
        return ConvertUtils.toStr(get(key));
    }

    public static String[] getStrArray(String key){
        return ConvertUtils.toStrArray(getString(key));
    }

    public static Boolean getBool(String key){
        return ConvertUtils.toBool(get(key));
    }

    public static Integer getInt(String key){
        return ConvertUtils.toInt(get(key));
    }

    public static Long getLong(String key){
        return ConvertUtils.toLong(get(key));
    }

    public static String getTempPath(){
        return PathUtils.normalize(getString(ConfigConstants.BASE_PATH) + PathUtils.UNIX_SEPARATOR_S + getString(ConfigConstants.TEMP_DIR));
    }

    public static String getStorePath(){
        return PathUtils.normalize(getString(ConfigConstants.BASE_PATH) + PathUtils.UNIX_SEPARATOR_S + getString(ConfigConstants.LOCAL_STORE));
    }
}

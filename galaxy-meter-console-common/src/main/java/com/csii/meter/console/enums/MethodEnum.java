package com.csii.meter.console.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 远程调用类型
 *
 * @author guoyu
 * @version 1.0
 * @date 2021/6/15 10:14 上午
 */
@Getter
@AllArgsConstructor
public enum MethodEnum {
    GET("get"),
    POST("post"),
    PUT("put"),
    DELETE("delete");
    private String value;
}

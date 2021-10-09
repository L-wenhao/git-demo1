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
public enum DataTypeEnum {
    INTEGER("integer"),
    Long("long"),
    STRING("string"),
    DOUBLE("double"),
    FLOAT("float"),
    BOOLEAN("boolean"),
    OBJECT("object"),
    ARRAY("array"),
    MAP("map"),
    DATE("date"),
    DATE_TIME("dateTime"),
    BIG_DECIMAL("bigDecimal");
    private String value;
}

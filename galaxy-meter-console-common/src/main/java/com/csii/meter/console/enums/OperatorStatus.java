package com.csii.meter.console.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 操作状态
 *
 * @author liuya
 */
@RequiredArgsConstructor
@Getter
public enum OperatorStatus {
    /**
     * 成功
     */
    SUCCESS(0, "SUCCESS"),

    /**
     * 失败
     */
    FAIL(0, "FAIL");

    private final int code;
    private final String name;
}

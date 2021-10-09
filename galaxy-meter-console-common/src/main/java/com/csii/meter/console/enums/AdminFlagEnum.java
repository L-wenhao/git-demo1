package com.csii.meter.console.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 发布状态枚举
 *
 * @author liuya
 */
@RequiredArgsConstructor
@Getter
public enum AdminFlagEnum {
    /**
     * 管理员
     */
    ADMIN(1, "管理员"),

    /**
     * 普通用户
     */
    USER(0, "普通用户");

    private final int code;
    private final String description;

    /**
     * 获取全部枚举
     */
    public static List<AdminFlagEnum> acquireList() {
        return Arrays.stream(AdminFlagEnum.values()).collect(Collectors.toList());
    }

    /**
     * 根据代码查找描述信息
     * @param code
     * @return
     */
    public static String getDescriptionByCode(final int code) {
        AdminFlagEnum enumObj = Arrays.stream(AdminFlagEnum.values())
                .filter(e -> e.getCode()==code).findFirst().orElse(null);
        return enumObj == null ? null : enumObj.getDescription();
    }
}

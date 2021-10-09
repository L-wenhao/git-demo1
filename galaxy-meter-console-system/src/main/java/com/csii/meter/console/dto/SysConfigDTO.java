package com.csii.meter.console.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class SysConfigDTO implements Serializable {
    /**
     * 主键
     */
    private String id;
    /**
     * 参数名称
     */
    private String name;

    /**
     * 参数键名
     */
    private String configKey;

    /**
     * 参数键值
     */
    private String configValue;

    /**
     * 是否禁用（0未开启，1开启）
     */
    private Integer enabled;
}

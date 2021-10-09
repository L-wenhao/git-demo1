package com.csii.meter.console.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class SysDictTypeDTO implements Serializable {
    /**
     * 主键
     */
    private String id;

    /**
     * 字典名称
     */
    private String name;

    /**
     * 字典类型
     */
    private String type;

    /**
     * 是否系统级,否(0),是(1)
     */
    private Integer systemFlag;

    /**
     * 是否禁用（0未开启，1开启）
     */
    private Integer enabled;
}

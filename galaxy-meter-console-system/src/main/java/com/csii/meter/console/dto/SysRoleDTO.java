package com.csii.meter.console.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class SysRoleDTO implements Serializable {
    /**
     * 角色ID
     */
    private String id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色类型（0系统默认，1自定义）
     */
    private Integer type;

    /**
     * 是否禁用（0未开启，1开启）
     */
    private Integer enabled;
}

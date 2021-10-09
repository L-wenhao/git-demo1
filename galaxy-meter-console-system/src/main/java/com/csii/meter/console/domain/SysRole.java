package com.csii.meter.console.domain;

import lombok.*;

/**
 * 角色信息对象 console_role
 *
 * @author liuya
 * @date 2020-09-20
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysRole extends BaseEntity {
    private static final long serialVersionUID = 1L;
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

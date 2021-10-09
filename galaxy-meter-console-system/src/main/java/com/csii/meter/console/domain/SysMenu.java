package com.csii.meter.console.domain;

import lombok.*;

/**
 * 菜单信息对象 console_menu
 *
 * @author liuya
 * @date 2020-09-20
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysMenu extends BaseEntity {
    private static final long serialVersionUID = 1L;
    /**
     * 菜单代码
     */
    private String menuCode;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 父菜单ID
     */
    private String parentId;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * URL
     */
    private String url;

    /**
     * 权限标识
     */
    private String perms;

    /**
     * 菜单类型（1目录 2菜单 3按钮）
     */
    private Integer type;

    /**
     * 打开方式（menuItem页签 menuBlank新窗口）
     */
    private String target;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 组件
     */
    private String component;

    /**
     * 是否禁用（0未开启，1开启）
     */
    private Integer enabled;
}

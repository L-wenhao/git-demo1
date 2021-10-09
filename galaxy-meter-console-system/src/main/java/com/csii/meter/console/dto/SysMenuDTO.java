package com.csii.meter.console.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class SysMenuDTO implements Serializable {
    /**
     * 菜单id
     */
    private String id;
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
     * 菜单类型（1目录 2菜单 3按钮）
     */
    private Integer type;
    /**
     * 打开方式（menuItem页签 menuBlank新窗口）
     */
    private String target;
    /**
     * 权限标识
     */
    private String perms;
    /**
     * 菜单图标
     */
    private String icon;
    /**
     * 菜单图标
     */
    private String component;
    /**
     * 备注
     */
    private String remark;
    /**
     * 是否禁用（0未开启，1开启）
     */
    private Integer enabled;
}

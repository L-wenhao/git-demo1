package com.csii.meter.console.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("菜单信息请求")
public class SysMenuRequest extends BaseRequest {
    /**
     * 菜单代码
     */
    @ApiModelProperty(value = "菜单代码", required = true, example = "code")
    private String menuCode;
    /**
     * 菜单名称
     */
    @ApiModelProperty(value = "菜单名称", required = true, example = "首页")
    private String name;
    /**
     * 父菜单ID
     */
    @ApiModelProperty(value = "父类id", required = true, example = "1")
    private String parentId;
    /**
     * 排序
     */
    @ApiModelProperty(value = "排序", required = true, example = "1")
    private Integer sort;
    /**
     * URL
     */
    @ApiModelProperty(value = "url", required = true, example = "/haha")
    private String url;
    /**
     * 菜单类型（1目录 2菜单 3按钮）
     */
    @ApiModelProperty(value = "菜单类型（1目录 2菜单 3按钮）", required = true, example = "1")
    private Integer type;
    /**
     * 打开方式（menuItem页签 menuBlank新窗口）
     */
    @ApiModelProperty(value = "打开方式（menuItem页签 menuBlank新窗口）", required = true, example = "menuItem")
    private String target;
    /**
     * 权限标识
     */
    @ApiModelProperty(value = "权限标识", required = true, example = "admin:product:add")
    private String perms;
    /**
     * 菜单图标
     */
    @ApiModelProperty(value = "icon", required = true, example = "xxx")
    private String icon;
    /**
     * 菜单图标
     */
    @ApiModelProperty(value = "component", required = true, example = "xxx")
    private String component;

    @ApiModelProperty(value = "备注", required = true, example = "xxx")
    private String remark;
    /**
     * 是否禁用（0未开启，1开启）
     */
    @ApiModelProperty(value = "备注", required = true, example = "是否禁用（0未开启，1开启）")
    private Integer enabled;
}

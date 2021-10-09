package com.csii.meter.console.vo;

import com.csii.meter.console.datamodel.TreeStructure;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel("菜单展示VO")
public class SysMenuVO extends BaseVO implements TreeStructure {
    /**
     * 菜单代码
     */
    @ApiModelProperty(value = "菜单代码")
    private String menuCode;
    /**
     * 菜单名称
     */
    @ApiModelProperty(value = "菜单名称")
    private String name;
    /**
     * 父菜单ID
     */
    @ApiModelProperty(value = "父菜单ID")
    private String parentId;
    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer sort;
    /**
     * URL
     */
    @ApiModelProperty(value = "URL")
    private String url;
    /**
     * 权限标识
     */
    @ApiModelProperty(value = "权限标识")
    private String perms;
    /**
     * 菜单类型（1目录 2菜单 3按钮）
     */
    @ApiModelProperty(value = "菜单类型（1目录 2菜单 3按钮）")
    private Integer type;
    /**
     * 打开方式（menuItem页签 menuBlank新窗口）
     */
    @ApiModelProperty(value = "打开方式（menuItem页签 menuBlank新窗口）")
    private String target;
    /**
     * 菜单图标
     */
    @ApiModelProperty(value = "菜单图标")
    private String icon;
    /**
     * 组件
     */
    @ApiModelProperty(value = "组件")
    private String component;
    /**
     * 是否禁用（0未开启，1开启）
     */
    @ApiModelProperty(value = "是否禁用（0未开启，1开启）")
    private Integer enabled;
    /**
     * 子节点
     */
    @ApiModelProperty(value = "子节点")
    private List<TreeStructure> children;

    /**
     * 是否有该菜单权限
     */
    private Boolean authority = false;
}

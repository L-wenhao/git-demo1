package com.csii.meter.console.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("角色展示VO")
public class SysRoleVO extends BaseVO {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色名称")
    private String name;

    @ApiModelProperty(value = "角色类型（0系统默认，1自定义）")
    private Integer type;

    @ApiModelProperty(value = "是否禁用（0未开启，1开启）")
    private Integer enabled;
}

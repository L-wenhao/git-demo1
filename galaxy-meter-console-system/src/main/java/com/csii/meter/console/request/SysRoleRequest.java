package com.csii.meter.console.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("角色数据请求")
public class SysRoleRequest extends BaseRequest {
    @ApiModelProperty(value = "角色名称", example = "管理员")
    private String name;

    @ApiModelProperty(value = "角色类型（0系统默认，1自定义）", example = "1")
    private Integer type;

    @ApiModelProperty(value = "是否禁用（0未开启，1开启）", example = "1")
    private Integer enabled;
}

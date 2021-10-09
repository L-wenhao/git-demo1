package com.csii.meter.console.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@ApiModel("用户角色展示VO")
public class SysIsHaveUserRoleVO implements Serializable {
    @ApiModelProperty(value = "角色ID")
    private String id;

    @ApiModelProperty(value = "角色名称")
    private String name;

    @ApiModelProperty(value = "是否拥有该角色,有(true),无(false)")
    private boolean status;
}

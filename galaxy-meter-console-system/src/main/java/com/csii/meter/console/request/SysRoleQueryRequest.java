package com.csii.meter.console.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("角色查询请求")
public class SysRoleQueryRequest extends BaseQueryRequest {
    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称", example = "管理员")
    private String name;

}

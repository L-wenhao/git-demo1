package com.csii.meter.console.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ApiModel("字典类型请求")
public class SysDictTypeRequest extends BaseRequest {
    /**
     * 字典类型
     */
    @NotBlank(message = "字典类型不能为空")
    @ApiModelProperty(value = "字典类型", example = "type")
    private String type;

    /**
     * 类型名称
     */
    @NotBlank(message = "类型名称不能为空")
    @ApiModelProperty(value = "类型名称", example = "描述")
    private String name;

    /**
     * 是否禁用（0未开启，1开启）
     */
    @ApiModelProperty(value = "是否禁用（0未开启，1开启）", example = "1")
    private Integer enabled;
}

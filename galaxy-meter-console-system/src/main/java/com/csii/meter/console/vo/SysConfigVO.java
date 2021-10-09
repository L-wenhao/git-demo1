package com.csii.meter.console.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("系统配置展示VO")
public class SysConfigVO extends BaseVO {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "参数名称")
    private String name;

    @ApiModelProperty(value = "参数键名")
    private String configKey;

    @ApiModelProperty(value = "参数键值")
    private String configValue;

    @ApiModelProperty(value = "是否禁用（0未开启，1开启）", example = "1")
    private Integer enabled;

}

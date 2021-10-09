package com.csii.meter.console.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@ApiModel("系统参数请求")
public class SysConfigRequest extends BaseRequest {
    /**
     * 参数名称
     */
    @ApiModelProperty(value = "参数名称", example = "参数名称")
    private String name;

    /**
     * 参数键名
     */
    @ApiModelProperty(value = "参数键名", example = "key")
    private String configKey;

    /**
     * 参数键值
     */
    @ApiModelProperty(value = "参数键值", example = "value")
    private String configValue;
    /**
     * 是否禁用（0未开启，1开启）
     */
    @ApiModelProperty(value = "是否禁用（0未开启，1开启）", example = "1")
    private Integer enabled;
}

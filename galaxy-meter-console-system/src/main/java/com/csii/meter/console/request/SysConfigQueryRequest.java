package com.csii.meter.console.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("系统参数查询请求")
public class SysConfigQueryRequest extends BaseQueryRequest {

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

}

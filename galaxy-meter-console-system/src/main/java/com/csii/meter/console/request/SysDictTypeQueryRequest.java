package com.csii.meter.console.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("字典类型查询请求")
public class SysDictTypeQueryRequest extends BaseQueryRequest {

    /**
     * 字典类型
     */
    @ApiModelProperty(value = "字典类型", example = "type")
    private String type;

    /**
     * 类型名称
     */
    @ApiModelProperty(value = "类型名称", example = "描述")
    private String name;


}

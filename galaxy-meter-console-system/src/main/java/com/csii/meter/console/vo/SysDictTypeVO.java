package com.csii.meter.console.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("字典类型展示VO")
public class SysDictTypeVO extends BaseVO {
    private static final long serialVersionUID = 1L;
    /**
     * 字典名称
     */
    @ApiModelProperty(value = "字典名称")
    private String name;

    /**
     * 字典类型
     */
    @ApiModelProperty(value = "字典类型")
    private String type;

    /**
     * 是否系统级,否(0),是(1)
     */
    @ApiModelProperty(value = "是否系统级,否(0),是(1)")
    private Integer systemFlag;

    /**
     * 是否禁用（0未开启，1开启）
     */
    @ApiModelProperty(value = "是否禁用（0未开启，1开启）")
    private Integer enabled;

}

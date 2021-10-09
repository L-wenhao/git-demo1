package com.csii.meter.console.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("字典数据展示VO")
public class SysDictDataVO extends BaseVO {
    private static final long serialVersionUID = 1L;
    /**
     * 字典类型ID
     */
    @ApiModelProperty(value = "字典类型ID")
    private String typeId;

    /**
     * 字典类型： 组装数据使用
     */
    @ApiModelProperty(value = "字典类型")
    private String type;

    /**
     * 是否默认（0否，1是）
     */
    @ApiModelProperty(value = "是否默认（0否，1是）")
    private Integer defaultFlag;

    /**
     * 字典code
     */
    @ApiModelProperty(value = "字典代码")
    private String dictCode;

    /**
     * 字典name
     */
    @ApiModelProperty(value = "字典名称")
    private String dictName;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer sort;

    /**
     * 是否系统默认,否(0),是(1)
     */
    @ApiModelProperty(value = "是否系统默认,否(0),是(1)")
    private Integer systemFlag;

    /**
     * 是否禁用（默认未开启：0未开启，1开启）
     */
    @ApiModelProperty(value = "是否禁用（默认未开启：0未开启，1开启）")
    private Integer enabled;

}

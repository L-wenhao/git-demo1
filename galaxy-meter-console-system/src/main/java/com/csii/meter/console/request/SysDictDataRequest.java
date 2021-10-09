package com.csii.meter.console.request;

import com.csii.meter.console.annotation.Add;
import com.csii.meter.console.annotation.Update;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ApiModel("字典数据请求")
public class SysDictDataRequest extends BaseRequest {
    /**
     * 字典类型
     */
    @NotBlank(message = "字典类型ID不能为空", groups = {Add.class, Update.class})
    @ApiModelProperty(value = "字典类型ID", example = "1234")
    private String typeId;

    /**
     * 是否默认（0否，1是）
     */
    @ApiModelProperty(value = "是否默认（0否，1是）", example = "0")
    private Integer defaultFlag;

    /**
     * 字典code
     */
    @NotBlank(message = "字典代码不能为空")
    @ApiModelProperty(value = "字典代码", example = "code")
    private String dictCode;

    /**
     * 字典name
     */
    @NotBlank(message = "字典名称不能为空")
    @ApiModelProperty(value = "字典名称", example = "代码")
    private String dictName;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序", example = "1")
    private Integer sort;

//    /**
//     * 是否系统默认,否(0),是(1)
//     */
//    @ApiModelProperty(value = "是否系统默认,否(0),是(1)", example = "0")
//    private Integer systemFlag;

    /**
     * 是否禁用（默认未开启：0未开启，1开启）
     */
    @ApiModelProperty(value = "是否禁用（默认未开启：0未开启，1开启）", example = "1")
    private Integer enabled;
}

package com.csii.meter.console.request;

import com.csii.meter.console.annotation.Update;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ApiModel("字典数据查询请求")
public class SysDictDataQueryRequest extends BaseQueryRequest {
    /**
     * 字典类型
     */
    @NotBlank(message = "字典类型ID不能为空", groups = Update.class)
    @ApiModelProperty(value = "字典类型ID", example = "1234")
    private String typeId;

    /**
     * 字典code
     */
    @NotBlank(message = "字典代码")
    @ApiModelProperty(value = "字典代码", example = "code")
    private String dictCode;

    /**
     * 字典name
     */
    @NotBlank(message = "字典名称")
    @ApiModelProperty(value = "字典名称", example = "代码")
    private String dictName;


}

package com.csii.meter.console.request;

import com.csii.meter.console.annotation.Update;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 请求基类
 * @author liuya
 */
@Getter
@Setter
public class BaseRequest implements Serializable {

    /**
     * id
     */
    @NotBlank(message = "ID不能为空", groups = {Update.class})
    @ApiModelProperty(value = "主键id", example = "20455668684")
    private String id;
}
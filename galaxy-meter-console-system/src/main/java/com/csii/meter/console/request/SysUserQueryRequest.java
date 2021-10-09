package com.csii.meter.console.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("用户查询请求")
public class SysUserQueryRequest extends BaseQueryRequest {

    /**
     * 登录用户名
     */
    @ApiModelProperty(value = "登录用户名", example = "admin")
    private String loginId;

    /**
     * 用户名姓名
     */
    @ApiModelProperty(value = "用户名姓名", example = "张三")
    private String userName;

}

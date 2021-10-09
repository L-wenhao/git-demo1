package com.csii.meter.console.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("用户修改密码请求")
public class SysUserModifyPasswordRequest extends BaseRequest {
    /**
     * 登录用户名.
     */
    @ApiModelProperty(value = "登录用户名", example = "admin")
    private String loginId;

    /**
     * 密码.
     */
    @ApiModelProperty(value = "登录密码", example = "a123123")
    private String password;

    /**
     * 密码.
     */
    @ApiModelProperty(value = "旧登录密码", example = "a123123")
    private String oldPassword;

}

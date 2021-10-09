package com.csii.meter.console.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("用户信息展示VO")
public class SysUserVO extends BaseVO {
    private static final long serialVersionUID = 1L;

    /**
     * 登录用户名
     */
    @ApiModelProperty(value = "登录用户名")
    private String loginId;

    /**
     * 用户名姓名
     */
    @ApiModelProperty(value = "用户名姓名")
    private String userName;

    /**
     * 电话
     */
    @ApiModelProperty(value = "电话")
    private String phone;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    private String email;

    /**
     * 是否管理员（0否，1是）
     */
    @ApiModelProperty(value = "是否管理员（0否，1是）")
    private Integer adminFlag;

    /**
     * 是否禁用（0未开启，1开启）
     */
    @ApiModelProperty(value = "是否禁用（0未开启，1开启）")
    private Integer enabled;
}

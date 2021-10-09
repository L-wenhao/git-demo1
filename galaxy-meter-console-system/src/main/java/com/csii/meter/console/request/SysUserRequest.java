package com.csii.meter.console.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@ApiModel("用户管理请求")
public class SysUserRequest extends BaseRequest {
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

    /**
     * 用户密码
     */
    @ApiModelProperty(value = "用户密码", example = "12345678")
    private String password;

    /**
     * 电话
     */
    @ApiModelProperty(value = "电话", example = "13812341234")
    private String phone;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱", example = "example@163.com")
    private String email;

    /**
     * 是否禁用（0未开启，1开启）
     */
    @ApiModelProperty(value = "是否禁用（0未开启，1开启）", example = "1")
    private Integer enabled;

    /**
     * 用户角色
     */
    @ApiModelProperty(value = "用户角色，多个，隔开", example = "1,2,3,4")
    private String roleIds;
}

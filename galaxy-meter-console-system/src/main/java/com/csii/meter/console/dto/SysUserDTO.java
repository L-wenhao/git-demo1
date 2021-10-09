package com.csii.meter.console.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class SysUserDTO implements Serializable {
    /**
     * 用户ID
     */
    private String id;
    /**
     * 登录用户名
     */
    private String loginId;

    /**
     * 用户名姓名
     */
    private String userName;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 是否管理员（0否，1是）
     */
    private Integer adminFlag;

    /**
     * 是否禁用（0未开启，1开启）
     */
    private Integer enabled;

    /**
     * 用户角色，多个，隔开
     */
    private String roleIds;
}

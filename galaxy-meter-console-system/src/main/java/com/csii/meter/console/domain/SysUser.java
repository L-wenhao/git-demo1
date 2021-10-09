package com.csii.meter.console.domain;

import lombok.*;

import java.util.Date;

/**
 * 用户信息对象 console_user
 *
 * @author liuya
 * @date 2020-09-20
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysUser extends BaseEntity {
    private static final long serialVersionUID = 1L;
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
     * 最后登录IP
     */
    private String loginIp;

    /**
     * 最后登录时间
     */
    private Date loginDate;

    public boolean isAdmin() {
        if (adminFlag == null) {
            return false;
        } else if (adminFlag == 1) {
            return true;
        }
        return false;
    }

}

package com.csii.meter.console.datamodel;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;

/**
 * 用户信息
 *
 * @author liuya
 * @date 2020-09-20
 */
@Getter
@Setter
public class User {

    /**
     * 主键id
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
     * 最后登录IP
     */
    private String loginIp;

    /**
     * 最后登录时间
     */
    private Timestamp loginDate;

    /**
     * 备注
     */
    private String remark;

    public boolean isAdmin() {
        if (adminFlag == null) {
            return false;
        } else if (adminFlag == 1) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", loginId='" + loginId + '\'' +
                ", userName='" + userName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", adminFlag=" + adminFlag +
                ", enabled=" + enabled +
                ", loginIp='" + loginIp + '\'' +
                ", loginDate=" + loginDate +
                ", remark='" + remark + '\'' +
                '}';
    }
}

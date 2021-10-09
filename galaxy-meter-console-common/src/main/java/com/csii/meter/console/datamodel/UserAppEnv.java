package com.csii.meter.console.datamodel;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UserAppEnv implements Serializable {

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 应用ID
     */
    private String appId;

    /**
     * 应用code
     */
    private String appCode;

    /**
     * 应用名称
     */
    private String appName;

    /**
     * 环境ID
     */
    private String envId;

    /**
     * 环境code
     */
    private String envCode;

    /**
     * 环境名称
     */
    private String envName;

}

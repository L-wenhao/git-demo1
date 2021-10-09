package com.csii.meter.console.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class SysOperLogDTO implements Serializable {
    /**
     * 主键id
     */
    private String id;
    /**
     * 业务类型
     */
    private String businessType;
    /**
     * 操作类型（0其它 1新增 2修改 3删除）
     */
    private Integer operType;
    /**
     * 请求方式
     */
    private String requestMethod;
    /**
     * 操作用户ID
     */
    private String operId;

    /**
     * 操作用户姓名
     */
    private String operName;
    /**
     * 主机IP
     */
    private String operIp;
}

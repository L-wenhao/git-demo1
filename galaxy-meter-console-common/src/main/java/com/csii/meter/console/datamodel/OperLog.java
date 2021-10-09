package com.csii.meter.console.datamodel;

import com.csii.meter.console.utils.ShiroUtils;
import com.csii.meter.console.utils.SnowflakeIdFactory;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 操作日志对象 console_oper_log
 *
 * @author liuya
 * @date 2020-09-20
 */
@Getter
@Setter
public class OperLog implements Serializable {
    private static final long serialVersionUID = 1L;

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
     * 方法名称
     */
    private String method;

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
     * 操作URL
     */
    private String operUrl;

    /**
     * 主机IP
     */
    private String operIp;

    /**
     * 请求参数
     */
    private String operParam;

    /**
     * 返回参数
     */
    private String jsonResult;

    /**
     * 操作状态（0正常 1异常）
     */
    private Integer status;

    /**
     * 返回码
     */
    private String resultCode;

    /**
     * 返回信息
     */
    private String resultMsg;

    /**
     * 操作时间
     */
    private Timestamp operTime;

    public static OperLog buildOperLog() {
        OperLog operLog = new OperLog();
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        operLog.setId(SnowflakeIdFactory.generateId().toString());
        operLog.setOperTime(currentTime);
        User shiroUser = ShiroUtils.getUser();
        if (null != shiroUser) {
            operLog.setOperName(shiroUser.getUserName());
            operLog.setOperId(shiroUser.getId());
        }
        return operLog;
    }
}

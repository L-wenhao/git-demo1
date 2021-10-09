package com.csii.meter.console.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("操作日志查询请求")
public class SysOperLogQueryRequest extends BaseQueryRequest {
    /**
     * 业务类型
     */
    @ApiModelProperty(value = "业务类型", example = "字典数据")
    private String businessType;
    /**
     * 操作类型（0其它 1新增 2修改 3删除）
     */
    @ApiModelProperty(value = "操作类型（0其它 1新增 2修改 3删除）", example = "1")
    private Integer operType;
    /**
     * 请求方式
     */
    @ApiModelProperty(value = "请求方式", example = "POST")
    private String requestMethod;
    /**
     * 操作用户姓名
     */
    @ApiModelProperty(value = "操作用户姓名", example = "张三")
    private String operName;
    /**
     * 主机IP
     */
    @ApiModelProperty(value = "主机IP", example = "172.30.15.50")
    private String operIp;
}

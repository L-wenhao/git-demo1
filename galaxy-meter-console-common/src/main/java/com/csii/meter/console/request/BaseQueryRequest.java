package com.csii.meter.console.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 请求基类
 * @author liuya
 */
@Getter
@Setter
public class BaseQueryRequest extends BaseRequest implements Serializable {
    /**
     * 当前页码
     */
    @ApiModelProperty(value = "当前页码", example = "1")
    private int pageNum = 1;
    /**
     * 每页数量
     */
    @ApiModelProperty(value = "每页数量", example = "10")
    private int pageSize = 10;
    /**
     * 排序列
     */
    @ApiModelProperty(value = "排序列", example = "id", hidden = true)
    private String orderByColumn;
    /**
     * 排序的方向 "desc" 或者 "asc".
     */
    @ApiModelProperty(value = "每页数量", example = "desc", hidden = true)
    private String isAsc;

}
package com.csii.meter.console.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

/**
 * <p>
 * 实体基础父类VO
 * </>
 *
 * @author tsw
 * @since 2021-07-05
 */
@Getter
@Setter
public class BaseVO {
    /**
     * 组件ID
     */
    @ApiModelProperty(value = "组件ID")
    private String id;
    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private String createdBy;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Timestamp createTime;

    /**
     * 更新人
     */
    @ApiModelProperty(value = "更新人")
    private String updatedBy;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Timestamp updateTime;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

}

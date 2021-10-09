

package com.csii.meter.console.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysDictDataDTO implements Serializable {

    private String id;
    /**
     * 字典类型ID
     */
    private String typeId;

    /**
     * 是否默认（0否，1是）
     */
    private Integer defaultFlag;

    /**
     * 字典code
     */
    private String dictCode;

    /**
     * 字典name
     */
    private String dictName;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 是否系统级,否(0),是(1)
     */
    private Integer systemFlag;

    /**
     * 是否禁用（默认未开启：0未开启，1开启）
     */
    private Integer enabled;
}

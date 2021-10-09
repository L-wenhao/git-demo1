package com.csii.meter.console.domain;

import lombok.*;

/**
 * <p>
 * 字典数据表 console_dict_data
 * </p>
 *
 * @author lja
 * @since 2021-05-20
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysDictData extends BaseEntity {

    private static final long serialVersionUID = 1L;
    /**
     * 字典类型:
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

package com.csii.meter.console.domain;

import lombok.*;

/**
 * 字典类型对象 console_dict_type
 *
 * @author liuya
 * @date 2020-09-20
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysDictType extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 字典名称
     */
    private String name;

    /**
     * 字典类型
     */
    private String type;

    /**
     * 是否系统级,否(0),是(1)
     */
    private Integer systemFlag;

    /**
     * 是否禁用（0未开启，1开启）
     */
    private Integer enabled;

}

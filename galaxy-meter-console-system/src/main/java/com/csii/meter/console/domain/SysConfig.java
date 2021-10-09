package com.csii.meter.console.domain;

import lombok.*;

/**
 * 系统配置对象 console_config
 *
 * @author liuya
 * @date 2020-09-20
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysConfig extends BaseEntity {

    private static final long serialVersionUID = 1L;
    /**
     * 参数名称
     */
    private String name;

    /**
     * 参数键名
     */
    private String configKey;

    /**
     * 参数键值
     */
    private String configValue;

    /**
     * 是否禁用（0未开启，1开启）
     */
    private Integer enabled;

}

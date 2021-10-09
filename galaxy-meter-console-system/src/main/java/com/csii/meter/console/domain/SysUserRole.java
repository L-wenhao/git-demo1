package com.csii.meter.console.domain;

import lombok.*;

/**
 * 用户角色关联对象 console_user_role
 *
 * @author liuya
 * @date 2020-09-20
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysUserRole extends BaseEntity {
    private static final long serialVersionUID = 1L;
    /**
     * 用户ID
     */
    private String userId;

    /**
     * 角色ID
     */
    private String roleId;
}

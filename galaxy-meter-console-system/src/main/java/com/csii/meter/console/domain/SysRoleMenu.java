package com.csii.meter.console.domain;

import lombok.*;

/**
 * 角色菜单关联对象 console_role_menu
 *
 * @author liuya
 * @date 2020-09-20
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysRoleMenu extends BaseEntity {
    private static final long serialVersionUID = 1L;
    /**
     * 角色ID
     */
    private String roleId;

    /**
     * 菜单ID
     */
    private String menuId;
}

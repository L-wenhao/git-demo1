package com.csii.meter.console.mapper;

import com.csii.meter.console.domain.SysMenu;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 菜单信息Mapper接口
 *
 * @author liuya
 * @date 2020-09-20
 */
@Repository
public interface SysMenuMapper {
    /**
     * 查询菜单信息
     *
     * @param id 菜单信息ID
     * @return 菜单信息
     */
    public SysMenu selectMenuById(String id);
    /**
     * 根据角色ID查询菜单信息
     *
     * @param roleId 菜单信息ID
     * @return 菜单信息
     */
    public List<SysMenu> selectMenuByRoleId(String roleId);
    /**
     * 查询菜单信息列表
     *
     * @param menu 菜单信息
     * @return 菜单信息集合
     */
    public List<SysMenu> selectMenuList(SysMenu menu);
    /**
     * 新增菜单信息
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public int insertMenu(SysMenu menu);
    /**
     * 修改菜单信息
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public int updateMenu(SysMenu menu);
    /**
     * 删除菜单信息
     *
     * @param id 菜单信息ID
     * @return 结果
     */
    public int deleteMenuById(String id);
    /**
     * 批量删除菜单信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteMenuByIds(String[] ids);

    /**
     * 根据菜单ID删除RoleMenu关联表
     *
     * @param menuId 菜单ID
     * @return 结果
     */
    public int deleteRoleMenuByMenuId(String menuId);

}

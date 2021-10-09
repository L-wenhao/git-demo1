package com.csii.meter.console.service;

import com.csii.meter.console.dto.SysMenuDTO;
import com.csii.meter.console.vo.SysMenuVO;

import java.util.List;

/**
 * 菜单信息Service接口
 *
 * @author liuya
 * @date 2020-09-20
 */
public interface SysMenuService {
    /**
     * 查询菜单信息
     *
     * @param id 菜单信息ID
     * @return 菜单信息
     */
    public SysMenuVO selectMenuById(String id);
    /**
     * 根据角色ID查询菜单信息
     *
     * @param roleId 角色ID
     * @return 菜单信息
     */
    public List<SysMenuVO> selectMenuByRoleId(String roleId);
    /**
     * 查询菜单信息列表
     *
     * @param dto 菜单信息
     * @return 菜单信息集合
     */
    public List<SysMenuVO> selectMenuList(SysMenuDTO dto);

    /**
     * 新增菜单信息
     *
     * @param dto 菜单信息
     * @return 结果
     */
    public int insertMenu(SysMenuDTO dto);

    /**
     * 修改菜单信息
     *
     * @param dto 菜单信息
     * @return 结果
     */
    public int updateMenu(SysMenuDTO dto);

    /**
     * 批量删除菜单信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteMenuByIds(String ids);

    /**
     * 删除菜单信息信息
     *
     * @param id 菜单信息ID
     * @return 结果
     */
    public int deleteMenuById(String id);
}

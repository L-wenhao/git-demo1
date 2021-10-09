package com.csii.meter.console.service;

import com.csii.meter.console.dto.SysRoleDTO;
import com.csii.meter.console.vo.SysRoleVO;

import java.util.List;

/**
 * 角色信息Service接口
 *
 * @author liuya
 * @date 2020-09-20
 */
public interface SysRoleService {
    /**
     * 查询角色信息
     *
     * @param id 角色信息ID
     * @return 角色信息
     */
    public SysRoleVO selectRoleById(String id);
    /**
     * 查询角色信息列表
     *
     * @param dto 角色信息
     * @return 角色信息集合
     */
    public List<SysRoleVO> selectRoleList(SysRoleDTO dto);
    /**
     * 根据用户ID查询角色信息列表
     *
     * @param userId 用户ID
     * @return 角色信息集合
     */
    public List<SysRoleVO> selectRoleListByUserId(String userId);
    /**
     * 新增角色信息
     *
     * @param dto 角色信息
     * @return 结果
     */
    public int insertRole(SysRoleDTO dto);

    /**
     * 修改角色信息
     *
     * @param dto 角色信息
     * @return 结果
     */
    public int updateRole(SysRoleDTO dto);

    /**
     * 批量删除角色信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteRoleByIds(String ids);

    /**
     * 删除角色信息信息
     *
     * @param id 角色信息ID
     * @return 结果
     */
    public int deleteRoleById(String id);
    /**
     * 给角色添加菜单
     * @param roleId
     * @param menuIdList
     * @return
     */
    int addMenuToRole(String roleId, List<String> menuIdList);

    /**
     * 删除角色菜单：根据角色id
     * @param roleId
     */
    int deleteRoleMenuByRoleId(String roleId);
}

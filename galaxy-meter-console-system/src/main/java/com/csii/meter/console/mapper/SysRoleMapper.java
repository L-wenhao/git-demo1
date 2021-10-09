package com.csii.meter.console.mapper;

import com.csii.meter.console.domain.SysRole;
import com.csii.meter.console.domain.SysRoleMenu;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色信息Mapper接口
 *
 * @author liuya
 * @date 2020-09-20
 */
@Repository
public interface SysRoleMapper {
    /**
     * 查询角色信息
     *
     * @param id 角色信息ID
     * @return 角色信息
     */
    public SysRole selectRoleById(String id);

    /**
     * 查询角色信息列表
     *
     * @param role 角色信息
     * @return 角色信息集合
     */
    public List<SysRole> selectRoleList(SysRole role);

    /**
     * 新增角色信息
     *
     * @param role 角色信息
     * @return 结果
     */
    public int insertRole(SysRole role);

    /**
     * 修改角色信息
     *
     * @param role 角色信息
     * @return 结果
     */
    public int updateRole(SysRole role);

    /**
     * 删除角色信息
     *
     * @param id 角色信息ID
     * @return 结果
     */
    public int deleteRoleById(String id);

    /**
     * 批量删除角色信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteRoleByIds(String[] ids);

    List<SysRole> selectRoleListByUserId(String userId);

    /**
     * 批量插入：角色菜单
     * @param roleMenuList
     * @return
     */
    int batchInsertRoleMenu(List<SysRoleMenu> roleMenuList);

    /**
     * 删除角色菜单：根据角色id
     * @param roleId
     * @return
     */
    int deleteRoleMenuByRoleId(String roleId);

}

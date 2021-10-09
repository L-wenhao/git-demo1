package com.csii.meter.console.service.impl;

import com.csii.meter.console.converter.SysRoleConverter;
import com.csii.meter.console.domain.SysRole;
import com.csii.meter.console.domain.SysRoleMenu;
import com.csii.meter.console.dto.SysRoleDTO;
import com.csii.meter.console.mapper.SysRoleMapper;
import com.csii.meter.console.service.SysRoleService;
import com.csii.meter.console.utils.ConvertUtils;
import com.csii.meter.console.vo.SysRoleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色信息Service业务层处理
 *
 * @author liuya
 * @date 2020-09-20
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {
    @Autowired
    private SysRoleMapper roleMapper;

    /**
     * 查询角色信息
     *
     * @param id 角色信息ID
     * @return 角色信息
     */
    @Override
    public SysRoleVO selectRoleById(String id) {
        return SysRoleConverter.INSTANCE.domain2vo(roleMapper.selectRoleById(id));
    }

    /**
     * 查询角色信息列表
     *
     * @param dto 角色信息
     * @return 角色信息
     */
    @Override
    public List<SysRoleVO> selectRoleList(SysRoleDTO dto) {
        SysRole role = SysRoleConverter.INSTANCE.dto2domain(dto);
        return SysRoleConverter.INSTANCE.domain2vo(roleMapper.selectRoleList(role));
    }

    /**
     * 查询用户拥有的角色
     *
     * @param userId 用户ID
     * @return
     */
    @Override
    public List<SysRoleVO> selectRoleListByUserId(String userId) {
        return SysRoleConverter.INSTANCE.domain2vo(roleMapper.selectRoleListByUserId(userId));
    }

    /**
     * 新增角色信息
     *
     * @param dto 角色信息
     * @return 结果
     */
    @Override
    public int insertRole(SysRoleDTO dto) {
        SysRole role = SysRoleConverter.INSTANCE.dto2domain(dto);
        role.preInsert();
        return roleMapper.insertRole(role);
    }

    /**
     * 修改角色信息
     *
     * @param dto 角色信息
     * @return 结果
     */
    @Override
    public int updateRole(SysRoleDTO dto) {
        SysRole role = SysRoleConverter.INSTANCE.dto2domain(dto);
        role.preUpdate();
        return roleMapper.updateRole(role);
    }

    /**
     * 删除角色信息对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteRoleByIds(String ids) {
        return roleMapper.deleteRoleByIds(ConvertUtils.toStrArray(ids));
    }

    /**
     * 删除角色信息信息
     *
     * @param id 角色信息ID
     * @return 结果
     */
    @Override
    public int deleteRoleById(String id) {
        return roleMapper.deleteRoleById(id);
    }

    /**
     * 给角色添加菜单
     *
     * @param roleId
     * @param menuIdList
     * @return
     */
    @Override
    public int addMenuToRole(String roleId, List<String> menuIdList) {
        List<SysRoleMenu> roleMenuList = new ArrayList<>();
        for (String menuId : menuIdList) {
            SysRoleMenu roleMenu = new SysRoleMenu();
            roleMenu.setRoleId(roleId);
            roleMenu.setMenuId(menuId);
            roleMenu.preInsert();
            roleMenuList.add(roleMenu);
        }
        return roleMapper.batchInsertRoleMenu(roleMenuList);
    }

    /**
     * 删除角色菜单：根据角色id
     *
     * @param roleId
     */
    @Override
    public int deleteRoleMenuByRoleId(String roleId) {
        return roleMapper.deleteRoleMenuByRoleId(roleId);
    }
}

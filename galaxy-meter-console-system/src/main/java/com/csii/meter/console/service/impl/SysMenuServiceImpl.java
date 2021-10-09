package com.csii.meter.console.service.impl;

import com.csii.meter.console.converter.SysMenuConverter;
import com.csii.meter.console.datamodel.TreeStructure;
import com.csii.meter.console.domain.SysMenu;
import com.csii.meter.console.dto.SysMenuDTO;
import com.csii.meter.console.mapper.SysMenuMapper;
import com.csii.meter.console.service.SysMenuService;
import com.csii.meter.console.utils.TreeUtils;
import com.csii.meter.console.vo.SysMenuVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 菜单信息Service业务层处理
 *
 * @author liuya
 * @date 2020-09-20
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {
    @Autowired
    private SysMenuMapper menuMapper;

    /**
     * 查询菜单信息
     *
     * @param id 菜单信息ID
     * @return 菜单信息
     */
    @Override
    public SysMenuVO selectMenuById(String id) {
        return SysMenuConverter.INSTANCE.domain2vo(menuMapper.selectMenuById(id));
    }

    /**
     * 根据角色ID查询菜单信息
     *
     * @param roleId 角色ID
     * @return
     */
    @Override
    public List<SysMenuVO> selectMenuByRoleId(String roleId) {
        return SysMenuConverter.INSTANCE.domain2vo(menuMapper.selectMenuByRoleId(roleId));
    }

    /**
     * 查询菜单信息列表
     *
     * @param dto 菜单信息
     * @return 菜单信息
     */
    @Override
    public List<SysMenuVO> selectMenuList(SysMenuDTO dto) {
        SysMenu menu = SysMenuConverter.INSTANCE.dto2domain(dto);
        return SysMenuConverter.INSTANCE.domain2vo(menuMapper.selectMenuList(menu));
    }

    /**
     * 新增菜单信息
     *
     * @param dto 菜单信息
     * @return 结果
     */
    @Override
    public int insertMenu(SysMenuDTO dto) {
        SysMenu menu = SysMenuConverter.INSTANCE.dto2domain(dto);
        menu.preInsert();
        return menuMapper.insertMenu(menu);
    }

    /**
     * 修改菜单信息
     *
     * @param dto 菜单信息
     * @return 结果
     */
    @Override
    public int updateMenu(SysMenuDTO dto) {
        SysMenu menu = SysMenuConverter.INSTANCE.dto2domain(dto);
        menu.preUpdate();
        return menuMapper.updateMenu(menu);
    }

    /**
     * 删除菜单信息对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public int deleteMenuByIds(String ids) {
        List<SysMenuVO> allMenu = SysMenuConverter.INSTANCE.domain2vo(menuMapper.selectMenuList(SysMenu.builder().build()));
        String[] split = ids.split(",");
        List<TreeStructure> deleteMenus = new ArrayList<>();
        int count = 0;
        for (String id : split) {
            List<TreeStructure> tree = TreeUtils.buildByRecursive(allMenu, id);
            deleteMenus.addAll(tree);
            //还需要删除本身节点
            count = count + this.deleteMenuById(id);
        }
        return count + deleteMenuAndChildren(deleteMenus);
    }

    private int deleteMenuAndChildren(List<TreeStructure> menus) {
        if (Objects.isNull(menus) || menus.isEmpty()) {
            return 0;
        }
        int count = 0;
        for (TreeStructure treeStructure : menus) {
            if (Objects.nonNull(treeStructure.getChildren())) {
                count = count + deleteMenuAndChildren(treeStructure.getChildren());
            }
            count = count + this.deleteMenuById(treeStructure.getId());
        }
        return count;
    }

    /**
     * 删除菜单信息信息
     *
     * @param id 菜单信息ID
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteMenuById(String id) {
        //删除角色菜单关联表
        menuMapper.deleteRoleMenuByMenuId(id);
        //删除菜单表
        return menuMapper.deleteMenuById(id);
    }
}

package com.csii.meter.console.controller;

import com.csii.meter.console.annotation.ApiExt;
import com.csii.meter.console.annotation.Log;
import com.csii.meter.console.converter.SysRoleConverter;
import com.csii.meter.console.datamodel.Result;
import com.csii.meter.console.datamodel.TreeStructure;
import com.csii.meter.console.dto.SysMenuDTO;
import com.csii.meter.console.dto.SysRoleDTO;
import com.csii.meter.console.enums.OperatorType;
import com.csii.meter.console.request.SysRoleQueryRequest;
import com.csii.meter.console.request.SysRoleRequest;
import com.csii.meter.console.service.SysMenuService;
import com.csii.meter.console.service.SysRoleService;
import com.csii.meter.console.utils.ParamsUtils;
import com.csii.meter.console.utils.TreeUtils;
import com.csii.meter.console.vo.SysMenuVO;
import com.csii.meter.console.vo.SysRoleVO;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 角色信息Controller
 *
 * @author liuya
 * @date 2020-09-20
 */
@RestController
@RequestMapping("/system/role")
@Api(tags = "系统管理-角色管理")
public class SysRoleController extends BaseController {
    @Autowired
    private SysRoleService roleService;
    @Autowired
    private SysMenuService menuService;
    /**
     * 查询角色信息列表
     */
    @RequiresPermissions("admin:role:list")
    @PostMapping("/list")
    @ApiOperation(value = "分页查询角色列表", notes = "分页查询角色列表")
    public Result<PageInfo<SysRoleVO>> list(@ApiExt(ignore={"id"}) @RequestBody SysRoleQueryRequest request) {
        startPage(request);
        List<SysRoleVO> list = roleService.selectRoleList(SysRoleConverter.INSTANCE.request2dto(request));
        return Result.success(getPageInfo(list));
    }

    /**
     * 新增保存角色信息
     */
    @RequiresPermissions("admin:role:add")
    @Log(businessType = "新增角色信息", operatorType = OperatorType.INSERT)
    @PostMapping("/add")
    @ApiOperation(value = "新增角色信息", notes = "新增角色信息")
    public Result<Void> addSave(@ApiExt(ignore={"id"})
            @RequestBody SysRoleRequest request) {
        ParamsUtils.checkParamsIsNull(request.getName(), "角色名称不能为空");
        return toAjax(roleService.insertRole(SysRoleConverter.INSTANCE.request2dto(request)));
    }

    /**
     * 修改保存角色信息
     */
    @RequiresPermissions("admin:role:edit")
    @Log(businessType = "修改角色信息", operatorType = OperatorType.UPDATE)
    @PostMapping("/editSave")
    @ApiOperation(value = "根据id修改角色", notes = "根据id修改角色")
    public Result<Void> editSave(@RequestBody SysRoleRequest request) {
        ParamsUtils.checkParamsIsNull(request.getId(), "角色ID不能为空");
        ParamsUtils.checkParamsIsNull(request.getName(), "角色名称不能为空");
        return toAjax(roleService.updateRole(SysRoleConverter.INSTANCE.request2dto(request)));
    }

    /**
     * 删除角色信息
     */
    @RequiresPermissions("admin:role:remove")
    @Log(businessType = "角色管理", operatorType = OperatorType.DELETE)
    @ApiOperation(value = "根据ID删除角色", notes = "根据ID删除角色")
    @PostMapping("/remove")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "角色ID(多个用','分割)", required = true, dataType = "String", paramType = "query", example = "1,2"),
    })
    public Result<Void> remove(@RequestParam("ids") String ids) {
        ParamsUtils.checkParamsIsNull(ids, "角色ID不能为空");
        return toAjax(roleService.deleteRoleByIds(ids));
    }
    /**
     * 查询所有角色信息
     */
    @PostMapping("/selectRole")
    @ApiOperation(value = "查询所有角色信息", notes = "查询所有角色信息")
    public Result<List<SysRoleVO>> selectRole() {
        List<SysRoleVO> list = roleService.selectRoleList(new SysRoleDTO());
        return Result.success(list);
    }
    /**
     * 查询角色是否拥有菜单
     */
    @PostMapping("/selectRoleMenuList")
    @ApiOperation(value = "查询角色菜单列表", notes = "查询角色是否拥有菜单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", value = "角色id", required = true, dataType = "String", paramType = "query", example = "1234"),
    })
    public Result<List<TreeStructure>> selectRoleMenuList(@RequestParam("roleId") String roleId) {
        ParamsUtils.checkParamsIsNull(roleId, "角色ID不能为空");
        //查询所有菜单信息
        List<SysMenuVO> allMenuList = menuService.selectMenuList(new SysMenuDTO());

        List<SysMenuVO> menuPermissionList = menuService.selectMenuByRoleId(roleId);
        if (CollectionUtils.isEmpty(menuPermissionList)) {
            //组装树形结构
            List<TreeStructure> menuTree = TreeUtils.buildByRecursive(allMenuList, "0");
            return Result.success(menuTree);
        }
        Map<String, SysMenuVO> map = menuPermissionList.stream().collect(Collectors.toMap(SysMenuVO::getId, v -> v, (k1, k2) -> k1));
        for (SysMenuVO menuVO : allMenuList) {
            if(map.containsKey(menuVO.getId())) {
                menuVO.setAuthority(true);
            }
        }

        //组装树形结构
        List<TreeStructure> menuTree = TreeUtils.buildByRecursive(allMenuList, "0");
        return Result.success(menuTree);
    }
    /**
     * 给角色添加菜单
     */
    @Log(businessType = "角色关联菜单", operatorType = OperatorType.INSERT)
    @PostMapping("/addMenuToRole")
    @ApiOperation(value = "给角色添加菜单", notes = "给角色添加菜单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", value = "角色id", required = true, dataType = "String", paramType = "query", example = "1"),
            @ApiImplicitParam(name = "menuIds", value = "菜单id(多个用 , 隔开）", required = true, dataType = "String", paramType = "query", example = "1,2"),
    })
    public Result<Void> addMenuToRole(@RequestParam("roleId") String roleId,@RequestParam("menuIds") String menuIds) {
        ParamsUtils.checkParamsIsNull(roleId, "角色ID不能为空");
        ParamsUtils.checkParamsIsNull(menuIds, "菜单ID不能为空");
        List<String> menuIdList = Arrays.asList(menuIds.split(","));
        roleService.deleteRoleMenuByRoleId(roleId);
        return toAjax(roleService.addMenuToRole(roleId, menuIdList));
    }

}

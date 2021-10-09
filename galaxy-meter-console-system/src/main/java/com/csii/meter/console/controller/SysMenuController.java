package com.csii.meter.console.controller;

import com.csii.meter.console.annotation.Log;
import com.csii.meter.console.converter.SysMenuConverter;
import com.csii.meter.console.datamodel.Result;
import com.csii.meter.console.datamodel.TreeStructure;
import com.csii.meter.console.dto.SysMenuDTO;
import com.csii.meter.console.enums.OperatorType;
import com.csii.meter.console.request.SysMenuRequest;
import com.csii.meter.console.service.SysMenuService;
import com.csii.meter.console.utils.ParamsUtils;
import com.csii.meter.console.utils.TreeUtils;
import com.csii.meter.console.vo.SysMenuVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单信息Controller
 *
 * @author liuya
 * @date 2020-09-20
 */
@RestController
@RequestMapping("/system/menu")
@Api(tags = "系统管理-菜单管理")
public class SysMenuController extends BaseController {
    @Autowired
    private SysMenuService menuService;
    /**
     * 查询菜单信息列表
     */
    @PostMapping("/list")
    @ApiOperation(value = "查询菜单列表", notes = "查询菜单列表")
    public Result<List<TreeStructure>> list() {
        List<SysMenuVO> list = menuService.selectMenuList(new SysMenuDTO());
        if (list == null || list.isEmpty()) {
            return Result.success(new ArrayList<>());
        }
        //组装树形结构
        List<TreeStructure> menuTree = TreeUtils.buildByRecursive(list, "0");
        return Result.success(menuTree);
    }

    /**
     * 新增保存菜单信息
     */
    @RequiresPermissions("admin:menu:add")
    @Log(businessType = "菜单信息", operatorType = OperatorType.INSERT)
    @PostMapping("/add")
    @ApiOperation(value = "添加菜单", notes = "添加菜单")
    public Result<Void> addSave(@RequestBody SysMenuRequest request) {
        ParamsUtils.checkParamsIsNull(request.getName(), "菜单名称不能为空");
        ParamsUtils.checkParamsIsNull(request.getUrl(), "请求路径不能为空");
        ParamsUtils.checkParamsIsNull(request.getParentId(), "父ID不能为空");
        ParamsUtils.checkParamsIsNull(request.getType(), "菜单类型不能为空");
        return toAjax(menuService.insertMenu(SysMenuConverter.INSTANCE.request2dto(request)));
    }

    /**
     * 修改保存菜单信息
     */
    @RequiresPermissions("admin:menu:edit")
    @Log(businessType = "菜单信息", operatorType = OperatorType.UPDATE)
    @PostMapping("/update")
    @ApiOperation(value = "编辑菜单", notes = "编辑菜单")
    public Result<Void> update(@RequestBody SysMenuRequest request) {
        ParamsUtils.checkParamsIsNull(request.getId(), "菜单ID不能为空");
        ParamsUtils.checkParamsIsNull(request.getName(), "菜单名称不能为空");
        ParamsUtils.checkParamsIsNull(request.getUrl(), "请求路径不能为空");
        ParamsUtils.checkParamsIsNull(request.getParentId(), "父ID不能为空");
        ParamsUtils.checkParamsIsNull(request.getType(), "菜单类型不能为空");
        return toAjax(menuService.updateMenu(SysMenuConverter.INSTANCE.request2dto(request)));
    }


    /**
     * 根据id查询菜单信息
     */
    @RequiresPermissions("admin:menu:list")
    @PostMapping("/selectById")
    @ApiOperation(value = "根据id查询菜单", notes = "根据id查询菜单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "菜单ID", required = true, dataType = "String", paramType = "query", example = "1"),
    })
    @ResponseBody
    public Result<SysMenuVO> selectById(@RequestParam("id") String id) {
        ParamsUtils.checkParamsIsNull(id, "菜单ID不能为空");
        SysMenuVO menu = menuService.selectMenuById(id);
        return Result.success(menu);
    }


    /**
     * 删除菜单信息
     */
    @RequiresPermissions("admin:menu:remove")
    @Log(businessType = "菜单信息", operatorType = OperatorType.DELETE)
    @PostMapping("/remove")
    @ApiOperation(value = "根据id删除菜单", notes = "根据id删除菜单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "id集合", required = true, dataType = "String", paramType = "query", example = "1,2"),
    })
    public Result<Void> remove(@RequestParam("ids") String ids) {
        ParamsUtils.checkParamsIsNull(ids, "菜单ID不能为空");
        return toAjax(menuService.deleteMenuByIds(ids));
    }
}

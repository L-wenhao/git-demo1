package com.csii.meter.console.controller;

import com.csii.meter.console.annotation.ApiExt;
import com.csii.meter.console.annotation.Log;
import com.csii.meter.console.converter.SysUserConverter;
import com.csii.meter.console.datamodel.Result;
import com.csii.meter.console.dto.SysRoleDTO;
import com.csii.meter.console.dto.SysUserDTO;
import com.csii.meter.console.enums.OperatorType;
import com.csii.meter.console.enums.ResultCode;
import com.csii.meter.console.exception.MeterConsoleException;
import com.csii.meter.console.request.SysUserModifyPasswordRequest;
import com.csii.meter.console.request.SysUserQueryRequest;
import com.csii.meter.console.request.SysUserRequest;
import com.csii.meter.console.service.SysRoleService;
import com.csii.meter.console.service.SysUserService;
import com.csii.meter.console.utils.ParamsUtils;
import com.csii.meter.console.vo.SysIsHaveUserRoleVO;
import com.csii.meter.console.vo.SysRoleVO;
import com.csii.meter.console.vo.SysUserVO;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

import static com.csii.meter.console.datamodel.UserRealm.encryptPassword;


/**
 * 用户信息Controller
 *
 * @author liuya
 * @date 2020-09-20
 */
@RestController
@RequestMapping("/system/user")
@Api(tags = "系统管理-用户管理")
public class SysUserController extends BaseController {
    @Autowired
    private SysUserService userService;
    @Autowired
    private SysRoleService roleService;

    /**
     * 查询用户信息列表
     */
    @RequiresPermissions("admin:user:list")
    @PostMapping("/listPage")
    @ApiOperation(value = "分页查询用户列表", notes = "分页查询用户列表")
    public Result<PageInfo<SysUserVO>> listPage(@RequestBody SysUserQueryRequest request) {
        startPage(request);
        SysUserDTO dto = SysUserConverter.INSTANCE.request2dto(request);
        List<SysUserVO> list = userService.selectUserList(dto);
        return Result.success(getPageInfo(list));
    }

    @PostMapping("/queryList")
    @ApiOperation(value = "查询所有用户列表", notes = "查询所有用户列表")
    public Result<List<SysUserVO>> queryList() {
        return Result.success(userService.selectUserList(new SysUserDTO()));
    }

    /**
     * 新增保存用户信息
     */
    @RequiresPermissions("admin:user:add")
    @Log(businessType = "新增用户", operatorType = OperatorType.INSERT)
    @PostMapping("/add")
    @ApiOperation(value = "添加用户", notes = "新增用户，默认启用")
    public Result<Void> addSave(@ApiExt(ignore = {"id"}, required = {"loginId", "userName", "password"})
                                @RequestBody SysUserRequest request) {
        if (userService.checkUnique(request.getLoginId()) > 0) {
            return Result.fail("登录用户名 '" + request.getLoginId() + "' 已存在");
        }
        SysUserDTO dto = SysUserConverter.INSTANCE.request2dto(request);
        //加密格式和shiro验证的加密方式保持一致
        String password = encryptPassword(request.getLoginId(), request.getPassword());
        dto.setPassword(password);
        return toAjax(userService.insertUser(dto));
    }

    /**
     * 修改保存用户信息
     */
    @RequiresPermissions("admin:user:edit")
    @Log(businessType = "用户信息", operatorType = OperatorType.UPDATE)
    @PostMapping("/edit")
    @ApiOperation(value = "修改用户", notes = "修改用户")
    @Transactional
    public Result<Void> editSave(@ApiExt(required = {"id"})
                                 @Valid @RequestBody SysUserRequest request) {
        ParamsUtils.checkParamsIsNull(request.getId(), "用户ID不能为空");
        SysUserDTO dto = SysUserConverter.INSTANCE.request2dto(request);
        dto.setPassword(null);
        dto.setLoginId(null);
        return toAjax(userService.updateUser(dto));
    }

    /**
     * 修改密码
     */
    @Log(businessType = "修改密码", operatorType = OperatorType.UPDATE)
    @PostMapping("/modifyPassword")
    @ApiOperation(value = "修改密码", notes = "修改密码")
    public Result<Void> modifyPassword(@ApiExt(required = {"id", "loginId", "password", "oldPassword"})
                                       @RequestBody @Valid SysUserModifyPasswordRequest request) {
        ParamsUtils.checkParamsIsNull(request.getId(), "用户ID不能为空");
        String oldPassword = encryptPassword(request.getLoginId(), request.getOldPassword());
        if (!userService.checkPassword(request.getId(), oldPassword)) {
            throw new MeterConsoleException(ResultCode.USER_OLDPASSWORD_EQ_PASSWORD_WRONG.getCode(),
                    ResultCode.USER_OLDPASSWORD_EQ_PASSWORD_WRONG.getMsg());
        }
        String newPassword = encryptPassword(request.getLoginId(), request.getPassword());
        return toAjax(userService.modifyPassword(request.getId(), newPassword));
    }

    /**
     * 删除用户信息
     */
    @RequiresPermissions("admin:user:remove")
    @PostMapping("/remove")
    @ApiOperation(value = "删除用户", notes = "删除用户，多个用“,”分割")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "id集合", required = true, dataType = "String", paramType = "query", example = "1234,1231")
    })
    public Result<Void> remove(String ids) {
        ParamsUtils.checkParamsIsNull(ids, "用户ID不能为空");
        return toAjax(userService.deleteUserByIds(ids));
    }

    /**
     * 查询用户信息
     */
    @GetMapping("/detail/{id}")
    @ApiOperation(value = "查询用户详情", notes = "查询用户详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "String", paramType = "path", example = "1234")
    })
    public Result<SysUserVO> detail(@PathVariable("id") String id) {
        ParamsUtils.checkParamsIsNull(id, "用户ID不能为空");
        return Result.success(userService.selectUserById(id));
    }

    /**
     * 校验唯一性
     */
    @GetMapping("/checkUnique/{loginId}")
    @ApiOperation(value = "检查唯一性", notes = "检查唯一性")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "loginId", value = "登录ID", required = true, dataType = "String", paramType = "path", example = "1234")
    })
    public Result checkUnique(@PathVariable("loginId") String loginId) {
        ParamsUtils.checkParamsIsNull(loginId, "登录ID不能为空");
        if(userService.checkUnique(loginId) > 0) {
            throw new MeterConsoleException("登录id已经存在！");
        }
        return Result.successWithoutData("校验成功");
    }


    /**
     * 给用户添加角色
     */
    @RequiresPermissions("admin:role:userAddRole")
    @Log(businessType = "给用户添加角色", operatorType = OperatorType.DELETE)
    @PostMapping("/userAddRole")
    @ApiOperation(value = "给用户添加角色", notes = "给用户添加角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, dataType = "int", paramType = "query", example = "1"),
            @ApiImplicitParam(name = "roleIds", value = "角色id,数组格式", required = false, dataType = "String", paramType = "query", example = "1,2"),
    })
    public Result userAddRole(@RequestParam("userId") String userId, @RequestParam("roleIds") String roleIds) {
        ParamsUtils.checkParamsIsNull(userId, "用户id不能为空!");
        //绑定角色
        String[] split = roleIds.split(",");
        List<String> strings = new ArrayList<>();
        for (String s : split) {
            strings.add(s);
        }
        userService.addRoles(userId, strings);
        return Result.success();
    }


    /**
     * 查询用户角色列表
     */
    @PostMapping("/selectUserRoleList")
    @ApiOperation(value = "查询用户角色列表", notes = "查询用户角色列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", dataType = "String", paramType = "query", example = "1"),
    })
    public Result<List<SysIsHaveUserRoleVO>> selectUserRoleList(@RequestParam("userId") String userId) {
        ParamsUtils.checkParamsIsNull(userId, "用户ID不能为空!");
        List<SysIsHaveUserRoleVO> resultList = new ArrayList<>();
        //查询所有角色
        List<SysRoleVO> roleVOList = roleService.selectRoleList(new SysRoleDTO());
        if (roleVOList == null || roleVOList.isEmpty()) {
            return Result.success(resultList);
        }
        Map<String, SysRoleVO> userRoleMap = new HashMap<>();
        List<SysRoleVO> userRoleList = roleService.selectRoleListByUserId(userId);
        if (Objects.nonNull(userRoleList)) {
            userRoleMap = userRoleList.stream().collect(Collectors.toMap(SysRoleVO::getId, role -> role));
        }
        for (SysRoleVO roleVO : roleVOList) {
            SysIsHaveUserRoleVO isHaveUserRoleVO = new SysIsHaveUserRoleVO();
            isHaveUserRoleVO.setId(roleVO.getId());
            isHaveUserRoleVO.setName(roleVO.getName());
            if (userRoleMap.containsKey(roleVO.getId())) {
                isHaveUserRoleVO.setStatus(true);
            } else {
                isHaveUserRoleVO.setStatus(false);
            }
            resultList.add(isHaveUserRoleVO);
        }
        return Result.success(resultList);
    }
}

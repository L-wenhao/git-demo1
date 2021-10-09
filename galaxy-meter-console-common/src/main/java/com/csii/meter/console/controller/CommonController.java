package com.csii.meter.console.controller;

import com.csii.meter.console.datamodel.*;
import com.csii.meter.console.enums.AdminFlagEnum;
import com.csii.meter.console.enums.ResultCode;
import com.csii.meter.console.service.ICommonService;
import com.csii.meter.console.utils.BeanUtils;
import com.csii.meter.console.utils.ServletUtils;
import com.csii.meter.console.utils.ShiroUtils;
import com.csii.meter.console.utils.TreeUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.stream.Collectors;

import static com.csii.meter.console.datamodel.UserRealm.encryptPassword;

/**
 * 公共Controller
 *
 * @author liuya
 * @date 2020-09-20
 */
@RestController
@Api(tags = "公共接口")
public class CommonController extends BaseController {

    @Value("${shiro.enable}")
    private boolean shiroEnable;
    @Value("${shiro.inactive-time:108000}")
    private int inactiveTime;
    @Autowired
    private ICommonService commonService;

    @PostMapping("/login")
    @ResponseBody
    @ApiOperation(value = "登录认证")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "loginId", value = "登录id", required = true, dataType = "String", paramType = "query", example = "admin"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String", paramType = "query", example = "123456")
    })
    public Result ajaxLogin(String loginId, String password) {
        if (!shiroEnable) {
            password = encryptPassword(loginId, password);
            User user = commonService.login(loginId, password);
            HttpSession session = ServletUtils.getSession();
            session.setAttribute("user", user);
            //session过期时间，单元秒
            session.setMaxInactiveInterval(inactiveTime);
            Map<String, Object> menuMap = getMenuInfo(user);
            return Result.success(menuMap);
        }
        UsernamePasswordToken token = new UsernamePasswordToken(loginId, password);
        Subject subject = SecurityUtils.getSubject();
        //单位毫秒
        subject.getSession().setTimeout(inactiveTime*1000);
        try {
            subject.login(token);
            User user = (User) subject.getPrincipal();
            Map<String, Object> menuMap = getMenuInfo(user);
            if (Objects.isNull(menuMap) || menuMap.isEmpty()) {
                return Result.fail("用户没有任何权限，请联系管理员！");
            }
            return Result.success(menuMap);
        } catch (AuthenticationException e) {
            String msg = "用户或密码错误";
            if (StringUtils.isNotEmpty(e.getMessage())) {
                msg = e.getMessage();
            }
            return Result.fail(msg);
        }
    }

    /**
     * 获取用户可以查看的菜单及按钮，前端使用
     * @param user
     * @return
     */
    private Map<String, Object> getMenuInfo(User user) {
        Map<String, Object> map = new HashMap<>();
        //查询出该用户下菜单
        List<UserMenu> menus = null;
        if (user.isAdmin()) {
            menus = commonService.selectCurrentUserMenuList(null);
        } else {
            menus = commonService.selectCurrentUserMenuList(user.getId());
        }
        List<TreeStructure> permissionTrees = new ArrayList<>();
        if(Objects.nonNull(menus)){
            menus.forEach(item -> {
                PermissionTree permissionTree = new PermissionTree();
                BeanUtils.copyProperties(item, permissionTree);
                permissionTrees.add(permissionTree);
            });
        }
        List<TreeStructure> permissionTreeList = TreeUtils.buildByRecursive(permissionTrees, "0");
        map.put("menuList", permissionTreeList);
        //找出按钮菜单
        List<UserMenu> getButtons = menus.stream().filter(item -> item.getType().equals(3)).collect(Collectors.toList());
        //找出按钮
        List<String> buttons = getButtons.stream().map(UserMenu::getUrl).collect(Collectors.toList());
        map.put("button", buttons);
        map.put("userName", user.getUserName());
        map.put("userId", user.getId());
        map.put("loginId", user.getLoginId());
        map.put("appCode", "");
        map.put("envCode", "");
        return map;
    }

    @PostMapping("/myAppAndEnv")
    @ApiOperation(value = "查询应用信息和环境信息", notes = "查询有权限的应用和环境信息")
    public Result<List<Map<String, Object>>> myAppAndEnv() {
        User user = ShiroUtils.getUser();
        String userId = null;
        if (user.getAdminFlag() != AdminFlagEnum.ADMIN.getCode()) {//是否管理员（0否，1是）
            userId = ShiroUtils.getUserId();
        }
        List<UserAppEnv> list = commonService.selectCurrentUserAppEnvList(userId);
        List<Map<String, Object>> result = new ArrayList<>();
        for (UserAppEnv record : list) {
            boolean bool = false;
            if (null != result && result.size() > 0) {
                for (Map<String, Object> appMap : result) {
                    String appCode = (String) appMap.get("code");
                    if (appCode.equals(record.getAppCode())) {
                        List<Map<String, Object>> envList = (List<Map<String, Object>>) appMap.get("envList");
                        Map<String, Object> envMap = new HashMap<>();
                        envMap.put("id", record.getEnvId());
                        envMap.put("code", record.getEnvCode());
                        envMap.put("name", record.getEnvName());
                        envList.add(envMap);
                        bool = true;
                        break;
                    }
                }
            }
            if (!bool) {
                Map<String, Object> appMap = new HashMap<>();
                appMap.put("id", record.getAppId());
                appMap.put("code", record.getAppCode());
                appMap.put("name", record.getAppName());
                List<Map<String, Object>> envList = new ArrayList<>();
                Map<String, Object> envMap = new HashMap<>();
                envMap.put("id", record.getEnvId());
                envMap.put("code", record.getEnvCode());
                envMap.put("name", record.getEnvName());
                envList.add(envMap);
                appMap.put("envList", envList);
                result.add(appMap);
            }
        }
        return Result.success(result);
    }

    @PostMapping("/saveAppAndEnv")
    @ApiOperation(value = "保存当前应用信息和环境信息", notes = "保存当前有权限的应用和环境信息")
    public Result<Void> saveAppAndEnv() {
        return Result.success();
    }


    @PostMapping("/logOut")
    @ResponseBody
    @ApiOperation(value = "退出登录")
    public Result<Void> logOut() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        HttpSession session = ServletUtils.getSession();
        session.removeAttribute("user");
        session.invalidate();
        return Result.success();
    }

    @RequiresGuest
    @GetMapping("/authError")
    public Result<Void> authError() {
        return Result.fail(ResultCode.UN_AUTHORISE);
    }

    @GetMapping("/error")
    public Result<Void> error() {
        return Result.fail(ResultCode.FAIL);
    }
}

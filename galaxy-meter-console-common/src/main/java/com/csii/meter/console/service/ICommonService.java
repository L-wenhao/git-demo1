package com.csii.meter.console.service;

import com.csii.meter.console.datamodel.OperLog;
import com.csii.meter.console.datamodel.User;
import com.csii.meter.console.datamodel.UserAppEnv;
import com.csii.meter.console.datamodel.UserMenu;

import java.util.List;
import java.util.Set;

/**
 * 用户登录接口
 *
 * @author liuya
 * @date 2020-09-20
 */
public interface ICommonService {
    /**
     * 用户登录
     *
     * @param loginId  登录用户名
     * @param password 用户密码
     * @return 用户信息
     */
    User login(String loginId, String password);
    /**
     * 查询用户权限集合
     *
     * @param userId 用户ID
     * @return 结果
     */
    Set<String> selectPermsByUserId(String userId);

    /**
     * 查询当前用户菜单
     * @param userId 用户ID
     * @return 结果
     */
    List<UserMenu> selectCurrentUserMenuList(String userId);

    /**
     * 查询当前用户应用环境
     * @param userId 用户ID
     * @return
     */
    List<UserAppEnv> selectCurrentUserAppEnvList(String userId);

    /**
     * 新增操作日志
     *
     * @param operLog 操作日志
     * @return 结果
     */
    int insertOperLog(OperLog operLog);
}

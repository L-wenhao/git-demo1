package com.csii.meter.console.mapper;

import com.csii.meter.console.datamodel.User;
import com.csii.meter.console.datamodel.OperLog;
import com.csii.meter.console.datamodel.UserAppEnv;
import com.csii.meter.console.datamodel.UserMenu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * 系统公共Mapper接口
 * @author liuya
 */
@Repository
public interface CommonMapper {
    /**
     * 用户名和密码查询用户信息
     * @param loginId 用户名
     * @param password 密码
     * @return 结果
     */
    User selectUserByLoginIdAndPass(@Param("loginId") String loginId, @Param("password") String password);

    /**
     * 更新登录信息
     * @param user
     */
    void updateLoginInfo(User user);
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
    List<UserMenu> selectCurrentUserMenuList(@Param("userId") String userId);

    /**
     * 查询当前用户应用环境
     * @param userId 用户ID
     * @return 结果
     */
    List<UserAppEnv> selectCurrentUserAppEnvList(@Param("userId") String userId);

    /**
     * 新增操作日志
     *
     * @param operLog 操作日志
     * @return 结果
     */
    int insertOperLog(OperLog operLog);

}

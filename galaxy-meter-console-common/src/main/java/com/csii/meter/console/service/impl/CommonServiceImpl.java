package com.csii.meter.console.service.impl;

import com.csii.meter.console.datamodel.User;
import com.csii.meter.console.datamodel.OperLog;
import com.csii.meter.console.datamodel.UserAppEnv;
import com.csii.meter.console.datamodel.UserMenu;
import com.csii.meter.console.enums.ResultCode;
import com.csii.meter.console.mapper.CommonMapper;
import com.csii.meter.console.service.ICommonService;
import com.csii.meter.console.utils.ShiroUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CommonServiceImpl implements ICommonService {

    @Autowired
    private CommonMapper commonMapper;

    @Override
    public User login(String loginId, String password) {
        User user = commonMapper.selectUserByLoginIdAndPass(loginId, password);
        if (null == user) {
            throw new AuthenticationException(ResultCode.USER_NOT_EXIST_OR_PASSWORD_WRONG.getMsg());
        }
        if (user.getEnabled() != 1) {
            throw new AuthenticationException(ResultCode.USER_NOT_ENABLED.getMsg());
        }
        user.setPassword(null);
        final String ip = ShiroUtils.getIp();
        user.setLoginIp(ip);
        user.setLoginDate(new Timestamp(System.currentTimeMillis()));
        commonMapper.updateLoginInfo(user);
        return user;
    }

    @Override
    public Set<String> selectPermsByUserId(String userId) {
        //查询角色具有的菜单信息
        Set<String> privilegeCodeSet = commonMapper.selectPermsByUserId(userId);
        if(CollectionUtils.isEmpty(privilegeCodeSet)) {
            return new HashSet<>();
        }
        return privilegeCodeSet;
    }

    @Override
    public List<UserMenu> selectCurrentUserMenuList(String userId) {
        return commonMapper.selectCurrentUserMenuList(userId);
    }

    @Override
    public List<UserAppEnv> selectCurrentUserAppEnvList(String userId) {
        return commonMapper.selectCurrentUserAppEnvList(userId);
    }

    @Override
    public int insertOperLog(OperLog operLog) {
        return commonMapper.insertOperLog(operLog);
    }
}

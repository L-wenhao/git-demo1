package com.csii.meter.console.datamodel;

import com.csii.meter.console.service.ICommonService;
import com.csii.meter.console.utils.ShiroUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * 自定义Realm 处理登录 权限
 * 
 * @author liuya
 */
public class UserRealm extends AuthorizingRealm
{
    private static final Logger log = LoggerFactory.getLogger(UserRealm.class);

    @Autowired
    private ICommonService userLoginService;

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0)
    {
        User user = ShiroUtils.getUser();
        // 角色列表：是否放置角色id？
        Set<String> roles = new HashSet<String>();
        // 功能列表
        Set<String> menus = new HashSet<String>();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 管理员拥有所有权限
        if (user.isAdmin()) {
            info.addStringPermission("*:*:*");
        }
        else {
            menus = userLoginService.selectPermsByUserId(user.getId());
            // 角色加入AuthorizationInfo认证对象
            info.setRoles(roles);
            // 权限加入AuthorizationInfo认证对象
            info.setStringPermissions(menus);
        }
        return info;
    }

    /**
     * 登录认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException
    {
        //开发模式
        if(token instanceof ShiroDebugAuthenticationToken){
            User debugUser = new User();
            debugUser.setAdminFlag(1);
            debugUser.setId("0");
            debugUser.setLoginId("debug_user");
            SimpleAuthenticationInfo debugInfo = new SimpleAuthenticationInfo(debugUser,  "debug_user", getName());
            return debugInfo;
        }
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String loginId = upToken.getUsername();
        String password = "";
        if (upToken.getPassword() != null) {
            password = encryptPassword(loginId, new String(upToken.getPassword()));
        }
        User user = userLoginService.login(loginId, password);
        user.setPassword(password);
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user,  new String(upToken.getPassword()), getName());
        return info;
    }

    public static void clearCachedAuthorization() {
        RealmSecurityManager rsm = (RealmSecurityManager) SecurityUtils.getSecurityManager();
        UserRealm realm = (UserRealm) rsm.getRealms().iterator().next();
        realm.clearCachedAuthorizationInfo();
    }

    /**
     * 清理缓存权限
     */
    public void clearCachedAuthorizationInfo()
    {
        this.clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
    }

    public static String encryptPassword(String loginId, String password) {
        return new Md5Hash(loginId + password).toHex();
    }

    public static void main(String[] args) {
        System.out.println(encryptPassword("admin", "123456"));
        System.out.println("========================");
    }

}

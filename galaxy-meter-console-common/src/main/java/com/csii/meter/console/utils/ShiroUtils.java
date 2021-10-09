package com.csii.meter.console.utils;

import com.csii.meter.console.datamodel.User;
import com.csii.meter.console.datamodel.ShiroDebugAuthenticationToken;
import com.csii.meter.console.exception.LoginException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * shiro 工具类
 *
 * @author liuya
 */
@Component
public class ShiroUtils {
    private static final Logger log = LoggerFactory.getLogger(ShiroUtils.class);

    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    public static Session getSession() {
        return SecurityUtils.getSubject().getSession();
    }

    public static void logout() {
        getSubject().logout();
    }


    private static boolean shiroEnable;

    @Value("${shiro.enable:true}")
    private void setEnable(boolean enable){
        ShiroUtils.shiroEnable = enable;
    }

    private static boolean isDebug;

    @Value("${shiro.isDebug:false}")
    private void setDebug(boolean isDebug){
        ShiroUtils.isDebug = isDebug;
    }

    public static User getUser() {
        User user = null;
        try {
            if(shiroEnable && isDebug) {
                Object obj = getSubject().getPrincipal();
                if (Objects.isNull(obj)) {
                    ShiroDebugAuthenticationToken token = new ShiroDebugAuthenticationToken("debug_user", "debug_user");
                    Subject subject = SecurityUtils.getSubject();
                    subject.login(token);
                    user = (User) subject.getPrincipal();
                    return user;
                }else{
                    return (User)obj;
                }
            } else if(shiroEnable){
                Object obj = getSubject().getPrincipal();
                if (Objects.nonNull(obj)) {
                    user = new User();
                    BeanUtils.copyBeanProp(user, obj);
                    return user;
                }
            }else{
                HttpServletRequest request = ServletUtils.getRequest();
                user = (User) request.getSession().getAttribute("user");
            }

        } catch (Exception e) {
            log.error("获取用户异常 err:"+e.getMessage());
            throw new LoginException("请重新登陆");
        }

        if(user == null){
            throw new LoginException("请重新登陆");
        }

        return user;
    }

    public static void setUser(User user) {
        Subject subject = getSubject();
        PrincipalCollection principalCollection = subject.getPrincipals();
        String realmName = principalCollection.getRealmNames().iterator().next();
        PrincipalCollection newPrincipalCollection = new SimplePrincipalCollection(user, realmName);
        // 重新加载Principal
        subject.runAs(newPrincipalCollection);
    }

    /**
     * 获取登录id
     * @return
     */
    public static String getLoginId() {
        User user = getUser();
        if (user == null) {
            return null;
        }
        return user.getLoginId();
    }

    public static String getUserId() {
        User user = getUser();
        if (user == null) {
            return null;
        }
        return user.getId();
    }

    public static String getLoginName() {
        User user = getUser();
        if (user == null) {
            return null;
        }
        return user.getUserName();
    }

    public static String getIp() {
        String ip = null;
        try {
            //ip = getSubject().getSession().getHost();
            HttpServletRequest request = ServletUtils.getRequest();
            ip = request.getRemoteHost();
        } catch (Exception e) {
            log.error("获取IP地址失败");
        }
        return ip;
    }

    public static boolean isDebug(){
        return isDebug;
    }

    public static String getSessionId() {
        return String.valueOf(getSubject().getSession().getId());
    }
}

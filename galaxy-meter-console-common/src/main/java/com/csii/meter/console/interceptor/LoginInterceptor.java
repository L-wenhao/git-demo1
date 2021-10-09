package com.csii.meter.console.interceptor;

import com.csii.meter.console.datamodel.User;
import com.csii.meter.console.datamodel.Result;
import com.csii.meter.console.enums.ResultCode;
import com.csii.meter.console.utils.JacksonUtils;
import com.csii.meter.console.utils.ServletUtils;
import com.csii.meter.console.utils.ShiroUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author dzy
 * @date
 */
@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Value("${shiro.enable}")
    private boolean shiroEnable;
    @Value("${shiro.inactive-time:108000}")
    private int inactiveTime;

    static String[] passUrls;
    static {
        //注册放行路径
        passUrls = new String[]{"/console/swagger",
                                "/console/webjars",
                                "/console/error",
                                "/console/login",
                                "/console/logOut",
                                "/console/myAppAndEnv",
                                "/console/authError",
                                "/console/swagger-resources",
                                "/console/transaction/list"};
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //todo 开发阶段，不进行登录权限校验
        if (1==1) {
            return true;
        }

        //检测当前环境，若为本次测试则放行
        /*if (EnvirUtil.isTestEnv()) {
            return true;
        }*/
        String requestURI = request.getRequestURI();
        log.info("请求地址:{}",requestURI);
        for(String url : passUrls){
            if(requestURI.startsWith(url)){
                return true;
            }
        }

        //todo 登陆认证首页接口
//        if(requestURI.startsWith("/index")){
//            //1.获取token信息
//
//            //2.解析token获取用户信息
//
//            //3.设置shiro setUser
//        }

//        if (requestURI.startsWith("/console/swagger") || requestURI.startsWith("/console/webjars") ||
//                requestURI.equals("/console/admin/login") || requestURI.startsWith("/console/swagger-resources") || requestURI.startsWith("/console/admin/authError")) {
//            return true;
//        }
        User user = ShiroUtils.getUser();
        if (user == null) {
            log.error("会话超时");
            return this.loginFail(response);
        } else {    //已经登录
            return true;
        }
    }

    private boolean loginFail(HttpServletResponse response) throws Exception {
        Result result = new Result(ResultCode.SESSION_TIMEOUT.getCode(), ResultCode.SESSION_TIMEOUT.getMsg());
        response.addHeader("sessionstatus", "timeOut");
        response.setContentType("application/json;charset=UTF-8");
        response.getOutputStream().write(JacksonUtils.obj2json(result).getBytes());
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }

    /**
     * 单点登陆验证
     */
    public void loginAuth(User user){
        if(shiroEnable){
            HttpSession session = ServletUtils.getSession();
            if(session != null){
                session.setAttribute("user", user);
                //session过期时间，单元秒
                session.setMaxInactiveInterval(inactiveTime);
            }

        }else{
            UsernamePasswordToken token = new UsernamePasswordToken(user.getLoginId(), user.getPassword());
            Subject subject = SecurityUtils.getSubject();
            //单位毫秒
            subject.getSession().setTimeout(inactiveTime*1000);
            subject.login(token);
        }
    }
}

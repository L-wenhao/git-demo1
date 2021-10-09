package com.csii.meter.console.utils;

import com.csii.meter.console.enums.ResultCode;
import com.csii.meter.console.exception.MeterConsoleException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 客户端工具类
 */
public class ServletUtils {
    /**
     * 获取String参数
     */
    public static String getParameter(String name) {
        return getRequest().getParameter(name);
    }

    /**
     * 获取Header String参数
     */
    public static String getHeaderParameter(String name, String defaultValue) {
        return ConvertUtils.toStr(getRequest().getHeader(name), defaultValue);
    }

    public static String getAppCode(){
        String appCode = getHeaderParameter("appCode", null);
        if(StringUtils.isBlank(appCode)){
            throw new MeterConsoleException(ResultCode.ILLEGAL_ARGUMENT.getCode(), "请求头appCode不能为空");
        }
        return appCode;
    }
    public static String getEnvCode(){
        String envCode = getHeaderParameter("envCode", null);
        if(StringUtils.isBlank(envCode)){
            throw new MeterConsoleException(ResultCode.ILLEGAL_ARGUMENT.getCode(), "请求头envCode不能为空");
        }
        return envCode;
    }

    /**
     * 获取String参数
     */
    public static String getParameter(String name, String defaultValue) {
        return ConvertUtils.toStr(getRequest().getParameter(name), defaultValue);
    }

    /**
     * 获取Integer参数
     */
    public static Integer getParameterToInt(String name) {
        return ConvertUtils.toInt(getRequest().getParameter(name));
    }

    /**
     * 获取Integer参数
     */
    public static Integer getParameterToInt(String name, Integer defaultValue) {
        return ConvertUtils.toInt(getRequest().getParameter(name), defaultValue);
    }

    /**
     * 获取request
     */
    public static HttpServletRequest getRequest() {
        return getRequestAttributes().getRequest();
    }

    /**
     * 获取response
     */
    public static HttpServletResponse getResponse() {
        return getRequestAttributes().getResponse();
    }

    /**
     * 获取session
     */
    public static HttpSession getSession() {
        return getRequest().getSession();
    }

    public static ServletRequestAttributes getRequestAttributes() {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        return (ServletRequestAttributes) attributes;
    }

    /**
     * 将字符串渲染到客户端
     *
     * @param response 渲染对象
     * @param string   待渲染的字符串
     * @return null
     */
    public static String renderString(HttpServletResponse response, String string) {
        try {
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 是否是Ajax异步请求
     *
     * @param request
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        String accept = request.getHeader("accept");
        if (accept != null && accept.indexOf("application/json") != -1) {
            return true;
        }

        String xRequestedWith = request.getHeader("X-Requested-With");
        if (xRequestedWith != null && xRequestedWith.indexOf("XMLHttpRequest") != -1) {
            return true;
        }

        String uri = request.getRequestURI();
        if (inStringIgnoreCase(uri, ".json", ".xml")) {
            return true;
        }

        String ajax = request.getParameter("__ajax");
        if (inStringIgnoreCase(ajax, "json", "xml")) {
            return true;
        }
        return false;
    }

    private static boolean inStringIgnoreCase(String str, String... strs) {
        if (str != null && strs != null) {
            String[] var2 = strs;
            int var3 = strs.length;
            for(int var4 = 0; var4 < var3; ++var4) {
                String s = var2[var4];
                if (str.equalsIgnoreCase(trim(s))) {
                    return true;
                }
            }
        }
        return false;
    }

    private static String trim(String str) {
        return str == null ? "" : str.trim();
    }
}

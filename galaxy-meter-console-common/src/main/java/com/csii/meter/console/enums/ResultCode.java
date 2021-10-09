package com.csii.meter.console.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ResultCode {
    SUCCESS("200", "操作成功"),
    FAIL("500", "操作失败"),
    RETRY_ERROR("501", "系统繁忙,请稍后重试"),
    ILLEGAL_ARGUMENT("8001", "参数非法"),
    LOGIN_FAIL("555", "登录失败"),
    SESSION_TIMEOUT("566", "您长时间未使用，系统已自动退出，请重新登录"),
    UN_AUTHENTICATED("9001", "用户没有登录"),
    UN_AUTHORISE("9002", "没有足够的权限"),
    USER_NOT_EXIST_OR_PASSWORD_WRONG("9003", "用户不存在或密码错误"),
    USER_NOT_ENABLED("9004", "用户被禁用"),
    USER_OLDPASSWORD_EQ_PASSWORD_WRONG("9005", "旧密码不正确");

    private final String code;
    private final String msg;

}

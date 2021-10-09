package com.csii.meter.console.datamodel;

import org.apache.shiro.authc.UsernamePasswordToken;

public class ShiroDebugAuthenticationToken extends UsernamePasswordToken {
    public ShiroDebugAuthenticationToken(String loginId, String password){
        super(loginId, password);
    }
}

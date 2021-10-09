package com.csii.meter.console.constants;

/**
 *
 * @author guoyu
 * @version 1.0
 * @date 2021/5/31 2:12 下午
 */
public interface CommonCode {
    Integer SUCCESS = 1;
    Integer FAIL = 0;

    String HTTP = "http";
    String RPC = "rpc";

    Integer SOURCE_GIT = 1;             //来源类型-git
    Integer SOURCE_CODE= 2;             //来源类型-源码包
    Integer SOURCE_DEPLOY_CODE = 3;     //来源类型-部署包
    Integer SOURCE_SCAFFOLD = 4;        //来源类型-脚手架
}

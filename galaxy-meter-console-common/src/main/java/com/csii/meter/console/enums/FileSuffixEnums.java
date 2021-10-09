package com.csii.meter.console.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 文件后缀字典枚举
 */
@Getter
@AllArgsConstructor
public enum FileSuffixEnums {
    TAR(".tar"),
    GZ(".gz"),
    TAR_GZ(".tar.gz"),
    TGZ(".tgz"),
    BZ2(".bz2"),
    TAR_BZ2(".tar.bz2"),
    Z(".Z"),
    TAR_Z(".tar.Z"),
    ZIP(".zip"),
    RAR(".rar");

    private String value;

}

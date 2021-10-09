package com.csii.meter.console.utils;

import com.csii.meter.console.enums.ResultCode;
import com.csii.meter.console.exception.MeterConsoleException;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class ParamsUtils {
    public static void checkParamsIsNull(Object obj, String msg) {
        if (obj == null || StringUtils.isBlank(obj.toString())) {
            throw new MeterConsoleException(ResultCode.ILLEGAL_ARGUMENT.getCode(), msg);
        }
    }

    public static void checkListIsNull(List<Object> list, String msg) {
        if (list == null || list.size() == 0) {
            throw new MeterConsoleException(ResultCode.ILLEGAL_ARGUMENT.getCode(), msg);
        }
    }
}

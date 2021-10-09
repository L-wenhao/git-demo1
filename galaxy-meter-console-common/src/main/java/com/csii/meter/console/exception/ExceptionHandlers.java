package com.csii.meter.console.exception;

import com.csii.meter.console.datamodel.Result;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * ControllerMethodResolver.
 */
@ControllerAdvice
public class ExceptionHandlers {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlers.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    protected Result serverExceptionHandler(final Exception exception) {
        LOGGER.error(exception.getMessage(), exception);
        String message = null;
        if (exception instanceof MeterConsoleException) {
            MeterConsoleException gatewayException = (MeterConsoleException) exception;
            message = gatewayException.getMessage();
            if (StringUtils.isNotEmpty(gatewayException.getErrorCode())) {
                return new Result(gatewayException.getErrorCode(), message);
            }
        }else if(exception instanceof LoginException){
            LoginException gatewayException = (LoginException) exception;
            message = gatewayException.getMessage();
            if (StringUtils.isNotEmpty(gatewayException.getErrorCode())) {
                return new Result(gatewayException.getErrorCode(), message);
            }
        }else if (exception instanceof MethodArgumentNotValidException) {
            //校验器校验异常，统一封装
            StringBuffer errStringBuffer = new StringBuffer();
            MethodArgumentNotValidException validException = (MethodArgumentNotValidException) exception;
            int i = 0;
            for (FieldError error : validException.getBindingResult().getFieldErrors()) {
                if (i > 0) {
                    errStringBuffer.append("; ").append(error.getDefaultMessage());
                } else {
                    errStringBuffer.append(error.getDefaultMessage());
                }
                i++;
            }
            return Result.fail("参数校验失败", errStringBuffer.toString());
        } else if (exception instanceof ConstraintViolationException) {
            //约束异常统一封装
            ConstraintViolationException constraintViolationException = (ConstraintViolationException) exception;
            Set<ConstraintViolation<?>> constraintViolations = constraintViolationException.getConstraintViolations();
            if (!CollectionUtils.isEmpty(constraintViolations)) {
                StringBuilder msgBuilder = new StringBuilder();
                for (ConstraintViolation constraintViolation : constraintViolations) {
                    msgBuilder.append(constraintViolation.getMessage()).append("; ");
                }
                message = msgBuilder.toString();
                if (message.length() > 1) {
                    message = message.substring(0, message.length() - 1);
                }
            }
            return Result.fail("参数校验失败", message);
        } else if (exception instanceof UnauthorizedException) {
            //设置返回消息提示
            message = "抱歉，当前用户未拥有该操作的权限";
        } else {
            message = "系统繁忙,请稍后重试";
        }
        return Result.fail(message);
    }
}

package com.csii.meter.console.datamodel;

import com.csii.meter.console.enums.ResultCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 操作消息提醒
 */
@Data
@ApiModel(description = "返回响应数据")
public class Result<T> {
    private static final long serialVersionUID = 1L;

    /**
     * 状态码
     */
    @ApiModelProperty(value = "响应码")
    private String code;

    /**
     * 返回内容
     */
    @ApiModelProperty(value = "响应信息")
    private String msg = "msg";

    /**
     * 数据对象
     */
    @ApiModelProperty(value = "返回对象")
    private T data;

    /**
     * 初始化一个新创建的 AjaxResult 对象，使其表示一个空消息。不允许返回空响应
     */
    private Result() {

    }

    /**
     * 没返回体的成功，自定义返回msg
     *
     * @param msg
     */
    public Result(String msg) {
        this.code = ResultCode.SUCCESS.getCode();
        this.msg = msg;
    }

    /**
     * 没返回体的响应
     *
     * @param code
     * @param msg
     */
    public Result(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 有返回体的响应
     *
     * @param code
     * @param msg
     * @param data
     */
    public Result(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }


    /**
     * 返回成功数据: 无返回体，msg为操作成功
     *
     * @return 成功消息
     */
    public static <T> Result<T> success() {
        return Result.successWithoutData(ResultCode.SUCCESS.getMsg());
    }

    /**
     * 返回成功数据: 无返回体，自定义msg
     *
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> Result<T> successWithoutData(String msg) {
        return Result.success(msg, null);
    }

    /**
     * 返回成功数据: 有返回体，自定义msg(msg可传空)
     *
     * @param msg
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(String msg, T data) {
        return new Result<>(ResultCode.SUCCESS.getCode(), msg, data);
    }

    /**
     * 返回成功数据，指定msg
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(T data) {
        return Result.success(ResultCode.SUCCESS.getMsg(), data);
    }

    /**
     * 返回失败数据:无返回体，ResultCode定义code、msg
     *
     * @param resultCode
     * @param <T>
     * @return
     */
    public static <T> Result<T> fail(ResultCode resultCode) {
        return new Result<>(resultCode.getCode(), resultCode.getMsg());
    }

    /**
     * 返回错误消息,指定msg
     *
     * @return
     */
    public static <T> Result<T> fail() {
        return Result.fail(ResultCode.FAIL.getMsg());
    }

    /**
     * 返回错误消息,自定义msg
     *
     * @param msg 返回内容
     * @return 警告消息
     */
    public static <T> Result<T> fail(String msg) {
        return Result.fail(msg, null);
    }

    /**
     * 返回错误消息，自定义msg，也可传入data
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static <T> Result<T> fail(String msg, T data) {
        return new Result<T>(ResultCode.FAIL.getCode(), msg, data);
    }
}

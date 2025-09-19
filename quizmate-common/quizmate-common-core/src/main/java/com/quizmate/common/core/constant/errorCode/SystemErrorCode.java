package com.quizmate.common.core.constant.errorCode;

/**
 * <h3>quizmate-backend</h3>
 * <p>系统错误码枚举类</p>
 * <p>统一使用 40xxx </p>
 *
 * @author moru
 * @since 2025-09-15 09:21
 */
public enum SystemErrorCode implements ErrorCode {

    API_NOT_EXIST(40001, "接口不存在"),
    REQUEST_METHOD_NOT_SUPPORT(40002, "请求方法不支持"),
    OPERATION_NOT_ALLOW(40003, "操作不允许"),
    REQUEST_PARAM_ERROR(40004, "请求参数错误"),
    REQUEST_PARAM_BIND_ERROR(40005, "请求参数绑定错误"),
    REQUEST_PARAM_MISSING(40006, "请求参数缺失"),
    SYSTEM_BUSY(40007, "系统繁忙，请稍后重试"),
    SYSTEM_TIMEOUT(40008, "系统请求超时，请稍后重试"),
    SYSTEM_ERROR(40009, "系统错误，请稍后重试");


    private final int code;

    private final String message;

    SystemErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getErrorCode() {
        return this.code;
    }

    @Override
    public String getErrorMessage() {
        return this.message;
    }
    }

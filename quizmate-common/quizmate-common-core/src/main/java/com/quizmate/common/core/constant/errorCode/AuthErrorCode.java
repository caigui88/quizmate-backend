package com.quizmate.common.core.constant.errorCode;

/**
 * <h3>quizmate-backend</h3>
 * <p>登录认证模块错误码</p>
 * <p>统一使用 10xxx </p>
 * @author moru
 * @since 2025-09-15 09:39
 */
public enum AuthErrorCode implements ErrorCode {

    NOT_LOGIN_ERROR(10001, "用户未登录，请先登录"),
    TOKEN_EXPIRED(10002, "登录状态已过期，请重新登录"),
    TOKEN_ERROR(10003, "登录状态异常，请重新登录"),
    USERNAME_OR_PASSWORD_ERROR(10004, "用户名或密码错误"),
    USERNAME_ALREADY_EXISTS(10005, "用户名已存在"),
    PHONE_ALREADY_EXISTS(10006, "手机号已存在"),
    EMAIL_ALREADY_EXISTS(10007, "邮箱已存在"),
    REGISTER_ERROR(10008, "用户注册失败，请稍后重试"),
    LOGOUT_ERROR(10009, "用户登出失败，请稍后重试"),
    PERMISSION_DENIED(10010, "权限认证异常"),
    ROLE_DENIED(10011, "角色认证异常"),
    ;
    private final int code;

    private final String message;


    AuthErrorCode(int code, String message) {
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

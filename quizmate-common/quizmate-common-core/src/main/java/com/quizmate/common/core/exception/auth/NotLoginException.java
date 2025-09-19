package com.quizmate.common.core.exception.auth;

import com.quizmate.common.core.exception.BaseException;

import static com.quizmate.common.core.constant.ServiceNameConstants.AUTH_SERVICE;
import static com.quizmate.common.core.constant.errorCode.AuthErrorCode.NOT_LOGIN_ERROR;

/**
 * <h3>quizmate-backend</h3>
 * <p>登录认证异常</p>
 *
 * @author moru
 * @since 2025-09-15 09:37
 */
public class NotLoginException extends BaseException {

    public static final BaseException INSTANCE = new NotLoginException(
            NOT_LOGIN_ERROR.getErrorCode(), NOT_LOGIN_ERROR.getErrorMessage()
    );

    public NotLoginException(int errorCode, String message) {
        super(errorCode, message, AUTH_SERVICE);
    }
}

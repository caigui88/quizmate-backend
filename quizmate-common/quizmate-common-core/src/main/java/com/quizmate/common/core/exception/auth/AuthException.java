package com.quizmate.common.core.exception.auth;

import com.quizmate.common.core.exception.BaseException;

/**
 * <h3>quizmate-backend</h3>
 * <p>认证/授权相关异常</p>
 *
 * @author moru
 * @since 2025-09-16 02:58
 */
public class AuthException extends BaseException {

    public AuthException(int errorCode, String message) {
        super(errorCode, message);
    }

    public AuthException(int errorCode, String message, String module) {
        super(errorCode, message, module);
    }
}

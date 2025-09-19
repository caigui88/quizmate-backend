package com.quizmate.common.core.exception.auth;

import com.quizmate.common.core.exception.BaseException;

import static com.quizmate.common.core.constant.ServiceNameConstants.AUTH_SERVICE;
import static com.quizmate.common.core.constant.errorCode.AuthErrorCode.ROLE_DENIED;

/**
 * <h3>quizmate-backend</h3>
 * <p>角色认证异常</p>
 *
 * @author moru
 * @since 2025-09-15 09:46
 */
public class NotRoleException extends BaseException {

    public static final NotRoleException INSTANCE = new NotRoleException(ROLE_DENIED.getErrorCode(), ROLE_DENIED.getErrorMessage());

    public NotRoleException(int errorCode, String message) {
        super(errorCode, message, AUTH_SERVICE);
    }
}

package com.quizmate.common.core.exception.auth;

import com.quizmate.common.core.exception.BaseException;

import static com.quizmate.common.core.constant.ServiceNameConstants.AUTH_SERVICE;
import static com.quizmate.common.core.constant.errorCode.AuthErrorCode.PERMISSION_DENIED;

/**
 * <h3>quizmate-backend</h3>
 * <p>权限认证异常</p>
 *
 * @author moru
 * @since 2025-09-15 09:43
 */
public class NotPermissionException extends BaseException {

    public static final NotPermissionException INSTANCE = new NotPermissionException(PERMISSION_DENIED.getErrorCode(), PERMISSION_DENIED.getErrorMessage());

    public NotPermissionException(int errorCode, String message) {
        super(errorCode, message, AUTH_SERVICE);
    }
}

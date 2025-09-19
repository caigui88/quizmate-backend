package com.quizmate.common.core.exception;

import com.quizmate.common.core.constant.errorCode.ErrorCode;
import lombok.Getter;

/**
 * <h3>quizmate-backend</h3>
 * <p>自定义异常类</p>
 *
 * @author moru
 * @since 2025-09-14 23:05
 */
@Getter
public class BusinessException extends BaseException {

    public BusinessException(int code, String message) {
        super(code, message);
    }

    public BusinessException(ErrorCode errorCode) {
        this(errorCode.getErrorCode(), errorCode.getErrorMessage());
    }

    public BusinessException(ErrorCode errorCode, String message) {
        this(errorCode.getErrorCode(), message);
    }
}

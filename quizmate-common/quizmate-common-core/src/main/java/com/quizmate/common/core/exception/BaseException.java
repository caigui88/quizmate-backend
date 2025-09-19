package com.quizmate.common.core.exception;

import lombok.Getter;

/**
 * <h3>quizmate-backend</h3>
 * <p>自定义异常基类</p>
 *
 * @author moru
 * @since 2025-09-14 23:09
 */
@Getter
public abstract class BaseException extends RuntimeException {
    /**
     * 错误码
     */
    private final int errorCode;

    private final String module;


    public BaseException(int errorCode, String message) {
        this(errorCode,message,"");
    }

    public BaseException(int errorCode, String message, String module) {
        super(message);
        this.errorCode = errorCode;
        this.module = module;
    }
}

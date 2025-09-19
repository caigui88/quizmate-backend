package com.quizmate.common.core.utils;

import com.quizmate.common.core.constant.errorCode.ErrorCode;
import com.quizmate.common.core.exception.BusinessException;

/**
 * <h3>quizmate-backend</h3>
 * <p>抛出异常工具类</p>
 *
 * @author moru
 * @since 2025-09-14 23:03
 */
public class ThrowUtils {

    /**
     * 如果条件为 true，则抛出指定的异常
     * @param condition 条件
     * @param exception 要抛出的异常
     * @throws RuntimeException 如果条件为 true，则抛出指定的异常
     */
    public static void throwIf(boolean condition, RuntimeException exception) {
        if (condition) {
            throw exception;
        }
    }
    /**
     * 条件成立则抛异常
     * 默认抛出的是业务异常
     *
     * @param condition
     * @param errorCode
     */
    public static void throwIf(boolean condition, ErrorCode errorCode) {
        throwIf(condition, new BusinessException(errorCode));
    }

    /**
     * 条件成立则抛异常
     * 默认抛出的是业务异常
     *
     * @param condition
     * @param errorCode
     * @param message
     */
    public static void throwIf(boolean condition, ErrorCode errorCode, String message) {
        throwIf(condition, new BusinessException(errorCode, message));
    }
}

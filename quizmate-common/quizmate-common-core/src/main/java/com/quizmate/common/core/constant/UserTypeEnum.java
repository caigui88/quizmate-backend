package com.quizmate.common.core.constant;

import lombok.Getter;

/**
 * <h3>quizmate-backend</h3>
 * <p> 用户类型枚举 </p>
 *
 * @author moru
 * @since 2025-09-16 03:09
 */
@Getter
public enum UserTypeEnum {
    /**
     * 普通用户
     */
    APP_USER(0, "app_user"),
    /**
     * 管理员
     */
    ADMIN_USER(1, "admin_user");

    private final Integer code;
    private final String description;

    UserTypeEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

}

package com.quizmate.common.core.constant;

import lombok.Getter;

/**
 * <h3>quizmate-backend</h3>
 * <p>时间单位常量类</p>
 *
 * @author moru
 * @since 2025-09-14 22:38
 */
@Getter
public enum TimeUnitConstant {

    /**
     * 一些常用的时间单位
     */
    MILLISECONDS("milliseconds", 1),

    SECONDS("seconds", 1000),

    MINUTES("minutes", 60 * 1000),

    HOURS("hours", 60 * 60 * 1000),

    DAYS("days", 24 * 60 * 60 * 1000);

    /**
     * 时间单位
     */
    private final String unit;

    /**
     * 对应的毫秒数
     */
    private final long millis;

    TimeUnitConstant(String unit, long millis) {
        this.unit = unit;
        this.millis = millis;
    }


}

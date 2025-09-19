package com.quizmate.common.core.constant;

/**
 * <h3>quizmate-backend</h3>
 * <p>Token 的 Key 常量</p>
 *
 * @author moru
 * @since 2025-09-14 23:23
 */
public class TokenConstants {
    /**
     * 令牌前缀
     */
    public static final String PREFIX_APP = "BearerApp ";   // 业务用户
    public static final String PREFIX_ADMIN = "BearerAdm "; // 后台用户

    /**
     * 令牌秘钥
     */
    public final static String SECRET = "abcdefghijklmnopqrstuvwxyz";

}

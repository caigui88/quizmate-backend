package com.quizmate.common.core.constant;

import static com.quizmate.common.core.constant.TimeUnitConstant.MINUTES;

/**
 * <h3>quizmate-backend</h3>
 * <p>缓存常量类</p>
 *
 * @author moru
 * @since 2025-09-14 22:36
 */
public interface CacheConstants {
    /**
     * 缓存有效期，默认720（分钟）
     */
    long EXPIRATION = 720 * MINUTES.getMillis();

    /**
     * 缓存刷新时间，默认120（分钟）
     */
    long REFRESH_TIME = 120 * MINUTES.getMillis();

    /**
     * 密码最大错误次数
     */
    int PASSWORD_MAX_RETRY_COUNT = 5;

    /**
     * 密码锁定时间，默认10（分钟）
     */
    long PASSWORD_LOCK_TIME = 10;

    /**
     * 管理员登录用户 token redis key
     */
    String ADMIN_LOGIN_TOKEN_KEY = "adm:login_tkn:";

    /**
     * 用户登录用户 token redis key
     */
    String APP_LOGIN_TOKEN_KEY = "app:login_tkn:";

    /**
     * 验证码 redis key
     */
    String CAPTCHA_CODE_KEY = "captcha_codes:";

    /**
     * 参数管理 cache key
     */
    String SYS_CONFIG_KEY = "sys_config:";

    /**
     * 字典管理 cache key
     */
    String SYS_DICT_KEY = "sys_dict:";

    /**
     * 登录账户密码错误次数 redis key
     */
    String PWD_ERR_CNT_KEY = "pwd_err_cnt:";

    /**
     * 登录IP黑名单 cache key
     */
    String SYS_LOGIN_BLACK_IP_LIST = SYS_CONFIG_KEY + "sys.login.blackIPList";

}

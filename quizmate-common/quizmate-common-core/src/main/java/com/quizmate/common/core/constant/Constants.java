package com.quizmate.common.core.constant;

/**
 * <h3>quizmate-backend</h3>
 * <p>通用常量信息类</p>
 *
 * @author moru
 * @since 2025-09-14 22:55
 */
public class Constants {

    /**
     * ----------------- 字符集 -----------------
     */
    public static final class Charset {
        public static final String UTF8 = "UTF-8";
        public static final String GBK = "GBK";
    }

    /**
     * ----------------- 协议 -----------------
     */
    public static final class Protocol {
        public static final String WWW = "www.";
        public static final String LOOKUP_RMI = "rmi:";
        public static final String LOOKUP_LDAP = "ldap:";
        public static final String LOOKUP_LDAPS = "ldaps:";
        public static final String HTTP = "http://";
        public static final String HTTPS = "https://";
    }

    /**
     * ----------------- 通用状态码 -----------------
     */
    public static final class Status {
        public static final Integer SUCCESS = 200;
        public static final Integer FAIL = 500;
    }

    /**
     * ----------------- 登录相关 -----------------
     */
    public static final class Login {
        public static final String LOGIN_SUCCESS_STATUS = "0";
        public static final String LOGIN_FAIL_STATUS = "1";
        public static final String LOGIN_SUCCESS = "Success";
        public static final String LOGOUT = "Logout";
        public static final String REGISTER = "Register";
        public static final String LOGIN_FAIL = "Error";
    }

    /**
     * ----------------- 分页相关 -----------------
     */
    public static final class Page {
        public static final String PAGE_NUM = "pageNum";
        public static final String PAGE_SIZE = "pageSize";
        public static final String ORDER_BY_COLUMN = "orderByColumn";
        public static final String IS_ASC = "isAsc";
    }

    /**
     * ----------------- 验证码相关 -----------------
     */
    public static final class Captcha {
        /**
         * 验证码有效期（分钟）
         */
        public static final long CAPTCHA_EXPIRATION = 2;
    }

    /**
     * ----------------- 资源路径 -----------------
     */
    public static final class Resource {
        /**
         * 资源映射路径 前缀
         */
        public static final String RESOURCE_PREFIX = "/profile";
    }

    /**
     * ----------------- JSON安全相关 -----------------
     */
    public static final class Json {
        /**
         * 自动识别json对象白名单配置
         */
        public static final String[] WHITELIST = {"com.quizmate"};
    }

    /**
     * ----------------- 定时任务安全相关 -----------------
     */
    public static final class Job {
        /**
         * 定时任务白名单配置
         */
        public static final String[] WHITELIST = {"com.quizmate.job.task"};
        /**
         * 定时任务违规字符
         */
        public static final String[] ERROR_STR = {
                "java.net.URL",
                "javax.naming.InitialContext",
                "org.yaml.snakeyaml",
                "org.springframework",
                "org.apache",
                "com.quizmate.common.core.utils.file"
        };
    }
}

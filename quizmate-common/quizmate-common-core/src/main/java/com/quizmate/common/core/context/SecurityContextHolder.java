package com.quizmate.common.core.context;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.quizmate.common.core.utils.StringUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <h3>quizmate-backend</h3>
 * <p>
 * 获取当前线程变量中的 用户id、用户名称、Token等信息
 * </p>
 * <p>
 * 注意： 必须在网关通过请求头的方法传入，同时在HeaderInterceptor拦截器设置值。 否则这里无法获取
 * </p>
 *
 * @author moru
 * @since 2025-09-14 23:25
 */
public class SecurityContextHolder {

    private static final TransmittableThreadLocal<Map<String, Object>> THREAD_LOCAL = new TransmittableThreadLocal<>();

    public static void set(String key, Object val) {
        Map<String, Object> map = getLocalMap();
        map.put(key, val == null ? StringUtils.EMPTY : val);
    }

    private static Map<String, Object> getLocalMap() {
        Map<String, Object> map = THREAD_LOCAL.get();
        if (map == null) {
            map = new ConcurrentHashMap<>();
            THREAD_LOCAL.set(map);
        }
        return map;
    }
}

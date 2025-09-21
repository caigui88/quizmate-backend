package com.quizmate.gateway.service;

/**
 * <h3>quizmate-backend</h3>
 * <p>验证码服务接口</p>
 *
 * @author moru
 * @since 2025-09-17 13:04
 */
public interface ValidateCodeService {

    void checkCaptcha(String string, String string1);
}

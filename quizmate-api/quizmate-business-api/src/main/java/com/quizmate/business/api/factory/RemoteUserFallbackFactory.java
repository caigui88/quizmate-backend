package com.quizmate.business.api.factory;

import com.quizmate.business.api.RemoteUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;

/**
 * <h3>quizmate</h3>
 * <p>业务用户服务降级处理</p>
 *
 * @author moru
 * @since 2025-09-02 21:08
 */
@Slf4j
public class RemoteUserFallbackFactory implements FallbackFactory<RemoteUserService> {
    @Override
    public RemoteUserService create(Throwable throwable) {
        log.error("业务用户服务调用失败:{}", throwable.getMessage());
        return new RemoteUserService() {
        };
    }
}

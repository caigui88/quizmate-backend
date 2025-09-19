package com.quizmate.gateway.handler;

import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

/**
 * <h3>quizmate-backend</h3>
 * <p>自定义限流异常处理</p>
 *
 * @author moru
 * @since 2025-09-17 23:11
 */
public class SentinelFallbackHandler implements WebExceptionHandler {



    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        return null;
    }
}

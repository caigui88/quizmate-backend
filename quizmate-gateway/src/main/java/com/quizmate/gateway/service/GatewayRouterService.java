package com.quizmate.gateway.service;

import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

/**
 * <h3>quizmate-backend</h3>
 * <p>动态路由加载服务接口</p>
 *
 * @author moru
 * @since 2025-09-19 09:00
 */
public interface GatewayRouterService {
    /**
     * 添加路由配置
     *
     * @param definition 路由信息
     * @return 是否成功
     */
    boolean add(RouteDefinition definition);

    /**
     * 更新路由配置
     *
     * @param definition 路由信息
     * @return 是否成功
     */
    boolean update(RouteDefinition definition);

    /**
     * 删除路由配置
     *
     * @param id 路由ID
     * @return 是否成功
     */
    Mono<ResponseEntity<Object>> delete(String id);

}

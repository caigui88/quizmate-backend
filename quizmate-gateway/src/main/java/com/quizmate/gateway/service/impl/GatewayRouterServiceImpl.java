package com.quizmate.gateway.service.impl;

import com.quizmate.gateway.service.GatewayRouterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import reactor.core.publisher.Mono;

/**
 * <h3>quizmate-backend</h3>
 * <p>动态路由加载服务实现类</p>
 *
 * @author moru
 * @since 2025-09-19 09:01
 */
@Service
@Slf4j
public class GatewayRouterServiceImpl implements GatewayRouterService, ApplicationEventPublisherAware {

    /**
     * 路由定义写入器
     */
    private final RouteDefinitionWriter routeDefinitionWriter;

    /**
     * 事件发布者
     */
    private ApplicationEventPublisher publisher;

    public GatewayRouterServiceImpl(RouteDefinitionWriter routeDefinitionWriter) {
        this.routeDefinitionWriter = routeDefinitionWriter;
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

    @Override
    public boolean add(RouteDefinition definition) {    // 新增加的路由
        log.info("增加路由配置项，新增路由 ID：{}", definition.getId()); // 日志打印
        try {
            this.routeDefinitionWriter.save(Mono.just(definition)).subscribe(); // 配置写入（订阅新的路由）
            this.publisher.publishEvent(new RefreshRoutesEvent(this));  // 发布路由事件
            return true;
        } catch (Exception e) {
            log.error("新增路由配置项失败，新增路由 ID：{}", definition.getId(), e);
            return false;
        }
    }

    @Override
    public Mono<ResponseEntity<Object>> delete(String routeID) { // 返回 Mono<Boolean>
        log.info("删除路由配置项，删除的路由 ID：{}", routeID);

        return this.routeDefinitionWriter.delete(Mono.just(routeID)) // 删除路由
                .then(Mono.defer(() -> Mono.just(ResponseEntity.ok().build()))) // 发布路由事件
                .onErrorResume(
                        t -> t instanceof NotFoundException, // 如果捕获到异常
                        r -> Mono.just(ResponseEntity.notFound().build())); // 返回 false
    }


    @Override
    public boolean update(RouteDefinition definition) {
        log.info("修改路由配置项，修改路由 ID：{}", definition.getId());
        try {
            this.delete(definition.getId());
            this.routeDefinitionWriter.save(Mono.just(definition)).subscribe(); // 配置写入（订阅新的路由）
            this.publisher.publishEvent(new RefreshRoutesEvent(this));  // 发布路由事件
            return true;
        } catch (Exception e) {
            log.error("修改路由配置项失败，修改路由 ID：{}", definition.getId(), e);
            return false;
        }
    }
}

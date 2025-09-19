package com.quizmate.gateway.controller;

import com.quizmate.common.core.domain.vo.AjaxResponse;
import com.quizmate.gateway.service.GatewayRouterService;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * <h3>quizmate-backend</h3>
 * <p>网关路由管理控制器接口</p>
 *
 * @author moru
 * @since 2025-09-19 10:34
 */
@RestController
@RequestMapping("/gateway/routes")
public class GatewayRouteController {

    private final GatewayRouterService routerService;

    public GatewayRouteController(GatewayRouterService routerService) {
        this.routerService = routerService;
    }

    @PostMapping("/add")
    public AjaxResponse add(@RequestBody RouteDefinition definition) {
        boolean result = routerService.add(definition);
        return result ? AjaxResponse.success("添加路由操作成功") : AjaxResponse.error("添加路由操作失败");
    }

    @PostMapping("/update")
    public AjaxResponse update(@RequestBody RouteDefinition definition) {
        boolean result = routerService.update(definition);
        return result ? AjaxResponse.success("更新路由操作成功") : AjaxResponse.error("更新路由操作失败");
    }

    @DeleteMapping("/delete/{routeId}")
    public AjaxResponse delete(@PathVariable String routeId) {
        Mono<ResponseEntity<Object>> delete = routerService.delete(routeId);
        return AjaxResponse.success(delete);
    }
}

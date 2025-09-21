package com.quizmate.gateway.config.dynamic_route.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <h3>quizmate-backend</h3>
 * <p>路由配置类</p>
 *
 * @author moru
 * @since 2025-09-19 09:35
 */
@ConfigurationProperties(prefix = "gateway.routes.config")
@Component
@Data
public class GatewayRouteConfigProperties {

    private String dataId;

    private String group;

    private String namespace;

}

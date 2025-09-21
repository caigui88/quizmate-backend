package com.quizmate.gateway.config.dynamic_route;

import com.alibaba.cloud.nacos.NacosConfigProperties;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import com.quizmate.gateway.config.dynamic_route.properties.GatewayRouteConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * <h3>quizmate-backend</h3>
 * <p>路由配置信息</p>
 * <p>将configService交由spring管理</p>
 * @author moru
 * @since 2025-09-16 00:03
 */
@Configuration
public class GatewayRoutesServiceConfig {

    @Autowired
    private GatewayRouteConfigProperties configProperties;

    @Autowired
    private NacosConfigProperties nacosConfigProperties;

    @Bean
    public ConfigService configService() throws NacosException {
        Properties properties = new Properties();
        properties.setProperty(PropertyKeyConst.SERVER_ADDR, nacosConfigProperties.getServerAddr());
        properties.setProperty(PropertyKeyConst.NAMESPACE, configProperties.getNamespace());
        return NacosFactory.createConfigService(properties);
    }
}

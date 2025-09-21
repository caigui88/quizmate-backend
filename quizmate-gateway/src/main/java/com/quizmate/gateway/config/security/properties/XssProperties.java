package com.quizmate.gateway.config.security.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * <h3>quizmate-backend</h3>
 * <p>XSS 跨站脚本配置类</p>
 *
 * @author moru
 * @since 2025-09-16 00:05
 */
@Setter
@Getter
@Configuration
@RefreshScope
@ConfigurationProperties(prefix = "security.xss")
public class XssProperties {
    /**
     * Xss开关
     */
    private Boolean enabled;

    /**
     * 排除路径
     */
    private List<String> excludeUrls = new ArrayList<>();

}

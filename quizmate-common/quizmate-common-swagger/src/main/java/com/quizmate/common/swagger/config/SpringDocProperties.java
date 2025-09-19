package com.quizmate.common.swagger.config;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * <h3>quizmate-backend</h3>
 * <p>Swagger 配置信息类 POJO</p>
 *
 * @author moru
 * @since 2025-09-14 21:45
 */
@Setter
@Getter
public class SpringDocProperties {
    /**
     * 网关地址
     */
    private String gatewayUrl;

    /**
     * 文档基本信息
     */
    @NestedConfigurationProperty
    private InfoProperties info = new InfoProperties();

    /**
     * <p>
     * 文档的基础属性信息
     * </p>
     *
     * @see io.swagger.v3.oas.models.info.Info
     * <p>
     * 为了 springboot 自动生产配置提示信息，所以这里复制一个类出来
     */
    @Setter
    @Getter
    public static class InfoProperties {
        /**
         * 标题
         */
        private String title = null;

        /**
         * 描述
         */
        private String description = null;

        /**
         * 联系人信息
         */
        @NestedConfigurationProperty
        private Contact contact = null;

        /**
         * 许可证
         */
        @NestedConfigurationProperty
        private License license = null;

        /**
         * 版本
         */
        private String version = null;

    }
}

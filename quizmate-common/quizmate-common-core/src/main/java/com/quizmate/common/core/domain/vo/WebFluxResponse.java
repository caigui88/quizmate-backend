package com.quizmate.common.core.domain.vo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * <h3>quizmate-backend</h3>
 * <p>WebFlux 响应包装类</p>
 *
 * @author moru
 * @since 2025-09-18 00:22
 */
@Setter
@Getter
public class WebFluxResponse {
    private final String contentType; // 默认值
    private final HttpStatus statusCode;       // 默认 HTTP 状态码
    private final Object data;                             // 响应主体
    private final int code;            // 默认业务码

    /**
     * 构造函数
     *
     * @param contentType 内容类型
     * @param statusCode      HTTP 状态码
     * @param data        响应主体
     * @param code        业务码
     */
    public WebFluxResponse(String contentType, HttpStatus statusCode, Object data, int code) {
        this.contentType = contentType;
        this.statusCode = statusCode;
        this.data = data;
        this.code = code;
    }

    // 一个简单的 Builder
    public static class Builder {
        private String contentType = "application/json"; // 默认值
        private HttpStatus status = HttpStatus.OK;       // 默认 HTTP 状态码
        private Object data;                             // 响应主体
        private int code = 200;            // 默认业务码

        public Builder contentType(String contentType) {
            this.contentType = contentType;
            return this;
        }

        public Builder status(HttpStatus status) {
            this.status = status;
            return this;
        }

        public Builder data(Object data) {
            this.data = data;
            return this;
        }

        public Builder code(int code) {
            this.code = code;
            return this;
        }

        public WebFluxResponse build() {
            return new WebFluxResponse(contentType, status, data, code);
        }
    }
}

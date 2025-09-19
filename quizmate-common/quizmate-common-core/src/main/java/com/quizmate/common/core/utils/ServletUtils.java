package com.quizmate.common.core.utils;

import com.quizmate.common.core.domain.vo.WebFluxResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

/**
 * <h3>quizmate-backend</h3>
 * <p>客户端工具类</p>
 *
 * @author moru
 * @since 2025-09-17 23:12
 */
@Slf4j
public class ServletUtils {

    public static String getParameter(String key) {
        return
    }

    public static HttpServletRequest getRequest() {
        try {
            return
        }
    }

    public static ServletRequestAttributes getRequestAttribute() {
        try {
            /*
             * 正常情况下，web 请求线程中
             * Spring MVC/WebFlux 处理请求时，会通过拦截器/过滤器，将当前请求 binding 到 ThreadLocal 里
             * 然后可以通过上下文处理器-RequestContextHolder 获取到请求的上下文
             * */
            RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
            return (ServletRequestAttributes) attributes;
        } catch (Exception e) {
            /*
             * 这里用了 try-catch，当拿不到请求上下文时，直接返回 null，而不是抛出异常
             * 目的是避免在非请求线程里调用工具方法时直接导致系统错误。
             *  */
            log.warn("No request attribute found in current thread,possibly called outside of a web request context");
            return null;
        }
    }

    public static String getHeader(HttpServletRequest request, String name) {
        ThrowUtils.throwIf(StringUtils.isBlank(name), new IllegalArgumentException("Header name must not be blank"));
        String value = request.getHeader(name);
        return StringUtils.isNotBlank(value) ? urlDecode(value) : StringUtils.EMPTY;
    }

    public static String urlEncode(String str) {
        if (StringUtils.isBlank(str)) {
            log.warn("String to encode is blank");
            return StringUtils.EMPTY;
        }

        try {
            return java.net.URLEncoder.encode(str, StandardCharsets.UTF_8);
        } catch (RuntimeException e) {
            log.error("URL encode error", e);
            return StringUtils.EMPTY;
        }
    }

    public static String urlDecode(String str) {
        if (StringUtils.isBlank(str)) {
            log.warn("String to decode is blank");
            return StringUtils.EMPTY;
        }

        try {
            return URLDecoder.decode(str, StandardCharsets.UTF_8);
        } catch (RuntimeException e) {
            log.error("URL decode error", e);
            return StringUtils.EMPTY;
        }
    }

    /**
     *
     * @param response
     * @param webFluxResponse
     * @return
     */
    public static Mono<Void> webFluxResponseWriter(ServerHttpResponse response, WebFluxResponse webFluxResponse) {
        response.setStatusCode(webFluxResponse.getStatusCode());
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE,webFluxResponse.getContentType());

    }
}

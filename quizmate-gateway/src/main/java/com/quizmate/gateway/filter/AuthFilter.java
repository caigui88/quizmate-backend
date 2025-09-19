package com.quizmate.gateway.filter;

import com.quizmate.common.core.constant.*;
import com.quizmate.common.core.exception.auth.AuthException;
import com.quizmate.common.core.utils.StringUtils;
import com.quizmate.common.core.utils.ThrowUtils;
import com.quizmate.common.redis.service.RedisService;
import com.quizmate.gateway.config.properties.IgnoreWhiteProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import static com.quizmate.common.core.constant.UserTypeEnum.APP_USER;

/**
 * <h3>quizmate-backend</h3>
 * <p>网关认证过滤器——通过过滤器注册统一认证组件</p>
 *
 * @author moru
 * @since 2025-09-16 00:06
 */
@Component
public class AuthFilter implements GlobalFilter, Ordered {
    private static final Logger log = LoggerFactory.getLogger(AuthFilter.class);

    // 排除过滤的 uri 地址，nacos自行添加
    @Autowired
    private IgnoreWhiteProperties ignoreWhite;

    @Autowired
    private RedisService redisService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpRequest.Builder mutate = request.mutate();

        String url = request.getURI().getPath();
        // 1. 预检请求放行——跳过不需要验证的路径
        if (StringUtils.matches(url, ignoreWhite.getWhites())) {
            // 2. 白名单路径放行
            return chain.filter(exchange);
        }
        String token = getToken(request);
        if (StringUtils.isEmpty(token)) {
            // 令牌为空，
        }

        return null;
    }

    private String getTokenCacheKey(String token, UserTypeEnum userType) {
        if(userType == APP_USER) {
            return CacheConstants.APP_LOGIN_TOKEN_KEY + token;
        } else if (userType == UserTypeEnum.ADMIN_USER) {
            return CacheConstants.ADMIN_LOGIN_TOKEN_KEY + token;
        } else {
            throw new AuthException(HttpStatus.UNAUTHORIZED, "用户类型错误");
        }
    }

    /**
     * 获取请求token
     * 前后端重要的一个信息沟通渠道就是 request 里携带的请求信息
     *
     * @param request 请求对象
     * @return 令牌
     */
    private String getToken(ServerHttpRequest request) {
        // 获取请求头中的令牌，
        String token = request.getHeaders().getFirst(SecurityConstants.AUTHORIZATION_HEADER);
        ThrowUtils.throwIf(StringUtils.isBlank(token), new AuthException(HttpStatus.UNAUTHORIZED, "请求未携带令牌"));
        if (token.startsWith(TokenConstants.PREFIX_ADMIN)) {
            // 管理端前缀
            token = token.replaceFirst(TokenConstants.PREFIX_ADMIN, StringUtils.EMPTY);
        } else if (token.startsWith(TokenConstants.PREFIX_APP)) {
            // 移动端前缀
            token = token.replaceFirst(TokenConstants.PREFIX_APP, StringUtils.EMPTY);
        } else {
            throw new AuthException(HttpStatus.UNAUTHORIZED, "令牌格式不正确");
        }
        return token;
    }

    @Override
    public int getOrder() {
        return -200;
    }
}

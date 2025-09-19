package com.quizmate.business.api;

import com.quizmate.business.api.factory.RemoteUserFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;

import static com.quizmate.common.core.constant.ServiceNameConstants.USER_SERVICE;

/**
 * <h3>quizmate</h3>
 * <p>业务服务用户远程调用接口</p>
 * <p>
 * 构建请求（URL 拼接、参数序列化）
 * <p>
 * 发送请求（HTTP 客户端、gRPC stub 等）
 * <p>
 * 接收响应（反序列化 JSON → Java 对象）
 * <p>
 * 错误处理（重试、超时、熔断降级）
 * <p>
 * 结果返回（包装成 DTO 给业务层）
 *
 * @author moru
 * @since 2025-08-30 18:04
 */
@FeignClient(contextId = "remoteUserService", value = USER_SERVICE, fallbackFactory = RemoteUserFallbackFactory.class)
public interface RemoteUserService {


}

package com.quizmate.business.api;

import com.quizmate.business.api.factory.RemoteQuestionBankFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;

import static com.quizmate.common.core.constant.ServiceNameConstants.QUESTION_BANK_SERVICE;

/**
 * <h3>quizmate</h3>
 * <p>远程调用题目题库接口</p>
 *
 * @author moru
 * @since 2025-09-08 23:44
 */
@FeignClient(value = QUESTION_BANK_SERVICE, contextId = "remoteQuestionBankService", fallbackFactory = RemoteQuestionBankFallbackFactory.class)
public interface RemoteQuestionBankService {
}

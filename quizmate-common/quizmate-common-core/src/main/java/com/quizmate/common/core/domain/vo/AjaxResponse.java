package com.quizmate.common.core.domain.vo;

import com.quizmate.common.core.constant.HttpStatus;
import org.apache.commons.lang3.ObjectUtils;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;

/**
 * <h3>quizmate-backend</h3>
 * <p></p>
 * 通用的响应封装类
 * 前后端响应统一使用此对象
 * 不仅能用于 WebFlux，也能用于传统 Spring MVC
 *
 * @author moru
 * @since 2025-09-17 13:06
 */
public class AjaxResponse extends HashMap<String, Object> implements Serializable {
    /**
     * 响应状态码的 key
     */
    public static final String CODE_TAG = "code";
    /**
     * 响应消息的 key
     */
    public static final String MSG_TAG = "msg";
    /**
     * 响应数据的 key
     */
    public static final String DATA_TAG = "data";
    /**
     * 默认成功消息
     */
    public static final String DEFAULT_SUCCESS_MSG = "操作成功";
    public static final String DEFAULT_ERROR_MSG = "操作失败";
    /**
     * 序列化版本号
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 初始化一个新创建的 AjaxResponse 对象，使其表示一个空消息。
     */
    public AjaxResponse() {
    }

    /**
     * 初始化一个新创建的 AjaxResponse 对象
     * 使其表示一个指定状态码和消息的消息。
     *
     * @param code 响应状态码
     * @param msg  响应消息
     */
    public AjaxResponse(int code, String msg) {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);

    }

    /**
     * 初始化一个新创建的 AjaxResponse 对象
     * 使其表示一个指定状态码、消息和数据的消息。
     *
     * @param code 响应状态码
     * @param msg  响应消息
     * @param data 响应数据
     */
    public AjaxResponse(int code, String msg, Object data) {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
        if (ObjectUtils.isNotEmpty(data)) {
            super.put(DATA_TAG, data);
        }
    }

    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static AjaxResponse success() {
        return AjaxResponse.success(HttpStatus.SUCCESS, DEFAULT_SUCCESS_MSG, null);
    }

    /**
     * 返回成功消息
     *
     * @param msg 响应消息
     * @return 成功消息
     */
    public static AjaxResponse success(String msg) {
        return AjaxResponse.success(msg, null);
    }

    /**
     * 返回成功消息
     *
     * @param data 响应数据
     * @return 成功消息
     */
    public static AjaxResponse success(Object data) {
        return AjaxResponse.success(DEFAULT_SUCCESS_MSG, data);
    }

    /**
     * 返回成功消息
     *
     * @param msg  响应消息
     * @param data 响应数据
     * @return 成功消息
     */
    public static AjaxResponse success(String msg, Object data) {
        return AjaxResponse.success(HttpStatus.SUCCESS, msg, data);
    }

    /**
     * 返回成功消息
     *
     * @param code 响应状态码
     * @param msg  响应消息
     * @param data 响应数据
     * @return 成功消息
     */
    public static AjaxResponse success(int code, String msg, Object data) {
        return new AjaxResponse(code, msg, data);
    }


    /**
     * 返回警告消息
     *
     * @param msg 响应消息
     * @return 成功消息
     */
    public static AjaxResponse warn(String msg) {
        return AjaxResponse.warn(msg, null);
    }

    /**
     * 返回警告消息
     *
     * @param msg  响应消息
     * @param data 响应数据
     * @return 成功消息
     */
    public static AjaxResponse warn(String msg, Object data) {
        return AjaxResponse.warn(HttpStatus.WARN, msg, data);
    }

    /**
     * 返回警告消息
     *
     * @param code 响应状态码
     * @param msg  响应消息
     * @param data 响应数据
     * @return 成功消息
     */
    public static AjaxResponse warn(int code, String msg, Object data) {
        return new AjaxResponse(code, msg, data);
    }

    /**
     * 返回错误消息
     *
     * @return 错误消息
     */
    public static AjaxResponse error() {
        return AjaxResponse.error(DEFAULT_ERROR_MSG);
    }

    /**
     * 返回错误消息
     *
     * @param msg 返回内容
     * @return 错误消息
     */
    public static AjaxResponse error(String msg) {
        return AjaxResponse.error(msg, null);
    }

    /**
     * 返回错误消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 错误消息
     */
    public static AjaxResponse error(String msg, Object data) {
        return new AjaxResponse(HttpStatus.ERROR, msg, data);
    }

    /**
     * 返回错误消息
     *
     * @param code 状态码
     * @param msg  返回内容
     * @return 错误消息
     */
    public static AjaxResponse error(int code, String msg) {
        return new AjaxResponse(code, msg, null);
    }

    /**
     * 是否成功
     *
     * @return 结果
     */
    public Boolean isSuccess() {
        return ObjectUtils.isNotEmpty(this.get(CODE_TAG)) && Objects.equals(HttpStatus.SUCCESS, this.get(CODE_TAG));
    }

    /**
     * 是否警告
     *
     * @return 结果
     */
    public Boolean isWarn() {
        return ObjectUtils.isNotEmpty(this.get(CODE_TAG)) && Objects.equals(HttpStatus.WARN, this.get(CODE_TAG));
    }

    /**
     * 是否错误
     *
     * @return 结果
     */
    public Boolean isError() {
        return ObjectUtils.isNotEmpty(this.get(CODE_TAG)) && Objects.equals(HttpStatus.ERROR, this.get(CODE_TAG));
    }

    /**
     * 方便链式调用
     *
     * @param key   键
     * @param value 值
     * @return 当前对象
     */
    public AjaxResponse put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}

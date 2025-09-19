package com.quizmate.common.redis.configure;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.Charset;
import com.alibaba.fastjson2.filter.Filter;

/**
 * <h3>quizmate-backend</h3>
 * <p>FastJson 序列化器</p>
 *
 * @author moru
 * @since 2025-09-14 22:06
 */
public class FastJson2JsonRedisSerializer<T> implements RedisSerializer<T> {

    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    static final Filter

    @Override
    public byte[] serialize(T t) throws SerializationException {
        return new byte[0];
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        return null;
    }
}

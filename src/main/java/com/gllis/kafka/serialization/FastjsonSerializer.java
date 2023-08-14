package com.gllis.kafka.serialization;

import com.alibaba.fastjson2.JSON;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.apache.kafka.common.serialization.Serializer;

import java.nio.charset.StandardCharsets;

/**
 * fastjson序列化
 *
 * @author glli
 * @date 2023/8/10
 */
public class FastjsonSerializer<T> implements Serializer<T> {

    @Override
    public byte[] serialize(String topic, T t) {

        return JSON.toJSONBytes(t);
    }

    @Override
    public byte[] serialize(String topic, Headers headers, T data) {
        headers.add(new RecordHeader("clazz", data.getClass().getName().getBytes(StandardCharsets.UTF_8)));
        return serialize(topic, data);
    }
}

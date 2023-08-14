package com.gllis.kafka.serialization;

import com.gllis.kafka.util.ProtostuffUtil;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.apache.kafka.common.serialization.Serializer;

import java.nio.charset.StandardCharsets;

/**
 * protostuff 序列化
 *
 * @author gllis
 * @date 2023/8/23
 */
public class ProtostuffSerializer<T> implements Serializer<T> {


    @Override
    public byte[] serialize(String topic, T obj) {
        LinkedBuffer buffer = LinkedBuffer.allocate();
        Schema schema = ProtostuffUtil.getSchema(obj.getClass());
        byte[] serializeData = ProtostuffIOUtil.toByteArray(obj, schema, buffer);
        buffer.clear();
        return serializeData;
    }

    @Override
    public byte[] serialize(String topic, Headers headers, T data) {
        if (data == null) {
            return null;
        }
        headers.add(new RecordHeader("clazz", data.getClass().getName().getBytes(StandardCharsets.UTF_8)));
        return serialize(topic, data);
    }


}

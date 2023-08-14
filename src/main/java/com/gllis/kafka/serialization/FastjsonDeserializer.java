package com.gllis.kafka.serialization;

import com.alibaba.fastjson2.JSON;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;

/**
 * fastjson 反序列化
 *
 * @author glli
 * @date 2023/8/10
 */
public class FastjsonDeserializer<T> implements Deserializer<T> {
    @Override
    public T deserialize(String topic, byte[] data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T deserialize(String topic, Headers headers, byte[] data) {
        // 从header中获取类名
        byte[] clazz = headers.lastHeader("clazz").value();
        if (clazz == null) {
            return null;
        }
        String clazzName = new String(clazz);
        if ("".equals(clazzName)) {
            return null;
        }
        if ("[B".equals(clazzName)) {
            return (T) data;
        }
        try {
            return (T) JSON.parseObject(new String(data), Class.forName(clazzName));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

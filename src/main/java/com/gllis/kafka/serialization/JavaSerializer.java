package com.gllis.kafka.serialization;

import org.apache.kafka.common.serialization.Serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Java对象序列化
 *
 * @author glli
 * @date 2023/8/10
 */
public class JavaSerializer implements Serializer<Object> {
    @Override
    public byte[] serialize(String topic, Object obj) {
        try {
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            ObjectOutputStream objectStream = new ObjectOutputStream(byteStream);
            objectStream.writeObject(obj);
            objectStream.flush();
            objectStream.close();
            return byteStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("Can't serialize object:" + obj, e);
        }
    }
}

package com.gllis.kafka.serialization;

import org.apache.kafka.common.serialization.Deserializer;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

/**
 * Java反序列化
 *
 * @author glli
 * @date 2023/8/10
 */
public class JavaDeserializer implements Deserializer {
    @Override
    public Object deserialize(String topic, byte[] data) {
        Object object = null;
        ByteArrayInputStream bis = new ByteArrayInputStream(data);
        try {
            ObjectInputStream ois = new ObjectInputStream(bis);
            object = ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }
}

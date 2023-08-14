package com.gllis.kafka.serialization;

import com.caucho.hessian.io.Hessian2Input;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * hessian 反充列化
 *
 * @author glli
 * @date 2023/8/14
 */
public class HessianDeserializer implements Deserializer<Object> {
    @Override
    public Object deserialize(String topic, byte[] data) {
        ByteArrayInputStream bis = new ByteArrayInputStream(data);
        try {
            Hessian2Input input = new Hessian2Input(bis);
            return input.readObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

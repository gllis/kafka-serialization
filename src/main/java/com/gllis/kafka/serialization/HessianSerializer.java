package com.gllis.kafka.serialization;

import com.caucho.hessian.io.Hessian2Output;
import org.apache.kafka.common.serialization.Serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Hessian 序列化
 * @author glli
 * @date 2023/8/14
 */
public class HessianSerializer implements Serializer {
    @Override
    public byte[] serialize(String topic, Object obj) {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            Hessian2Output output = new Hessian2Output();
            output.writeObject(obj);
            output.getBytesOutputStream().flush();
            output.completeMessage();
            output.close();
            return os.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}

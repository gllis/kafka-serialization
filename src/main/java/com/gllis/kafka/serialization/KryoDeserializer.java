package com.gllis.kafka.serialization;

import com.esotericsoftware.kryo.io.Input;
import com.gllis.kafka.util.KryoUtil;
import org.apache.kafka.common.serialization.Deserializer;

/**
 * kryo反序列化
 *
 * @author gllis
 * @date 2023/8/10
 */
public class KryoDeserializer implements Deserializer<Object> {

    @Override
    public Object deserialize(String topic, byte[] data) {
        Input input = new Input(data);
        return KryoUtil.kryoLocal.get().readClassAndObject(input);
    }
}

package com.gllis.kafka.serialization;

import com.esotericsoftware.kryo.io.Output;
import com.gllis.kafka.util.KryoUtil;
import org.apache.kafka.common.serialization.Serializer;

/**
 * kryo序列化
 *
 * @author glli
 * @date 2023/8/10
 */
public class KryoSerializer implements Serializer<Object> {

    @Override
    public byte[] serialize(String topic, Object obj) {
        Output output = new Output(128, -1);
        KryoUtil.kryoLocal.get().writeClassAndObject(output, obj);
        output.flush();
        return output.toBytes();
    }




}

package com.gllis.kafka.serialization;

import com.gllis.kafka.util.FstUtil;
import org.apache.kafka.common.serialization.Deserializer;

/**
 * FST 反序列化
 *
 * @author glli
 * @date 2023/8/14
 */
public class FstDeserializer implements Deserializer<Object> {
    @Override
    public Object deserialize(String topic, byte[] data) {
        return FstUtil.getConf().asObject(data);
    }
}

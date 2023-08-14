package com.gllis.kafka.serialization;

import com.gllis.kafka.util.FstUtil;
import org.apache.kafka.common.serialization.Serializer;

/**
 * FST 序列化
 *
 * @author glli
 * @date 2023/8/14
 */
public class FstSerializer implements Serializer<Object> {
    @Override
    public byte[] serialize(String topic, Object obj) {

        return FstUtil.getConf().asByteArray(obj);
    }
}

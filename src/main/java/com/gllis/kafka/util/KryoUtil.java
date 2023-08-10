package com.gllis.kafka.util;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.serializers.TaggedFieldSerializer;

import java.util.ArrayList;
import java.util.List;

/**
 * kryo 工具类
 *
 * @author glli
 * @date 2023/8/10
 */
public class KryoUtil {

    private static List<String> registerClassList = new ArrayList<>();

    public static final ThreadLocal<Kryo> kryoLocal = ThreadLocal.withInitial(() -> {
        Kryo kryo = new Kryo();
        kryo.setRegistrationRequired(false);
        return kryo;
    });

}

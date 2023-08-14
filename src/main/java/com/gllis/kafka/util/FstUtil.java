package com.gllis.kafka.util;

import org.nustaq.serialization.FSTConfiguration;

/**
 * Fst 工具
 * @author glli
 * @date 2023/8/14
 */
public class FstUtil {
    private static FSTConfiguration conf = FSTConfiguration.createAndroidDefaultConfiguration();

    public static FSTConfiguration getConf() {
        return conf;
    }
}

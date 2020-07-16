package com.zixuan.kafka.encoder;

import com.zixuan.kafka.utils.BeanUtils;

import java.util.Map;

public class ObjectDecoder implements org.apache.kafka.common.serialization.Deserializer {

    public void configure(Map configs, boolean isKey) {

    }

    public Object deserialize(String topic, byte[] data) {
        return BeanUtils.BytesToObject(data);
    }

    public void close() {

    }
}

package com.zixuan.kafka.encoder;

import com.zixuan.kafka.utils.BeanUtils;

import java.util.Map;

public class ObjectEncoder implements org.apache.kafka.common.serialization.Serializer {

    public void configure(Map configs, boolean isKey) {

    }

    public byte[] serialize(String topic, Object data) {
        return BeanUtils.ObjectToBytes(data);
    }

    public void close() {

    }
}

package com.zixuan.kafka.encoder;

import com.zixuan.kafka.utils.BeanUtils;

import java.util.Map;

public class ObjectDecoder implements org.apache.kafka.common.serialization.Serializer {
    public Object fromBytes(byte[] bytes) {
        return BeanUtils.BytesToObject(bytes);
    }

    public void configure(Map configs, boolean isKey) {

    }

    public byte[] serialize(String topic, Object data) {
        return new byte[0];
    }

    public void close() {

    }
}

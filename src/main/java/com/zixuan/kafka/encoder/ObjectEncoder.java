package com.zixuan.kafka.encoder;

import com.zixuan.kafka.utils.BeanUtils;

public class ObjectEncoder implements kafka.serializer.Encoder<Object> {
    public byte[] toBytes(Object o) {

        return BeanUtils.ObjectToBytes(o);
    }
}

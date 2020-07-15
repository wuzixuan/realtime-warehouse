package com.zixuan.kafka.encoder;

import com.zixuan.kafka.utils.BeanUtils;

public class ObjectDecoder implements kafka.serializer.Decoder<Object> {
    public Object fromBytes(byte[] bytes) {
        return BeanUtils.BytesToObject(bytes);
    }
}

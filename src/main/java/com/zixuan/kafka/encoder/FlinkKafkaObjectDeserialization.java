package com.zixuan.kafka.encoder;

import com.zixuan.kafka.utils.BeanUtils;
import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.typeinfo.TypeInformation;

import java.io.IOException;

public class FlinkKafkaObjectDeserialization implements DeserializationSchema<Object> {
    public Object deserialize(byte[] bytes) throws IOException {
        return BeanUtils.BytesToObject(bytes);
    }

    public boolean isEndOfStream(Object o) {
        return false;
    }

    public TypeInformation getProducedType() {
        return null;
    }
}

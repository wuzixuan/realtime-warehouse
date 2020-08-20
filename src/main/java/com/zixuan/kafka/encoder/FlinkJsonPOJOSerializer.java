package com.zixuan.kafka.encoder;


import com.zixuan.kafka.utils.BeanUtils;
import org.apache.flink.api.common.serialization.SerializationSchema;
import org.codehaus.jettison.json.JSONObject;

public class FlinkJsonPOJOSerializer<T> implements SerializationSchema<T> {

    public byte[] serialize(T o) {

        return BeanUtils.ObjectToBytes(o);
    }
}

package com.zixuan.kafka.encoder;


import org.apache.commons.lang.SerializationException;
import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper;
import org.codehaus.jettison.json.JSONObject;

import java.io.IOException;

public class FlinkJsonPOJODeserializer<T> implements DeserializationSchema<T> {

    private ObjectMapper objectMapper = new ObjectMapper();
    private Class<T> tClass;

    public T deserialize(byte[] bytes) throws IOException {
        if (bytes == null)
            return null;

        T data;
        try {
            data = objectMapper.readValue(bytes, tClass);
        } catch (Exception e) {
            throw new SerializationException(e);
        }

        return data;
    }

    public boolean isEndOfStream(Object o) {
        return false;
    }

    public TypeInformation getProducedType() {
        return TypeInformation.of(JSONObject.class);
    }
}

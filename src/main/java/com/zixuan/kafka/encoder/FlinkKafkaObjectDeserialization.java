package com.zixuan.kafka.encoder;

import com.zixuan.kafka.bean.OrderBean;
import com.zixuan.kafka.utils.BeanUtils;
import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.typeinfo.TypeHint;
import org.apache.flink.api.common.typeinfo.TypeInformation;

import java.io.IOException;

public class FlinkKafkaObjectDeserialization implements DeserializationSchema<Object> {
    public Object deserialize(byte[] bytes) throws IOException {
        return BeanUtils.BytesToObject(bytes);
    }

    //是否是最后一条数据，因为使用的是无界流而非批处理，所以不存在最后一条数据，直接返回false
    public boolean isEndOfStream(Object o) {
        return false;
    }

    //指定数据类型
    public TypeInformation getProducedType() {
       return  TypeInformation.of(OrderBean.class);
    }
}

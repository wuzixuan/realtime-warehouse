package com.zixuan.kafka.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtils {

    //读取生产者Properties文件
    public Properties loadProducerProp(Properties props,String encoderClass){
        return loadProp(props,"producer.properties",encoderClass);
    }

    //读取消费者Properties文件
    public Properties loadConsumerProp(Properties props,String decoderClass){
        return loadProp(props,"comsumer.properties",decoderClass);
    }

    //读取Properties文件
    private Properties loadProp(Properties props,String propsName,String coderClass){

        InputStream fis = this.getClass().getClassLoader().getResourceAsStream(propsName);
        try {
            props.load(fis);
            props.put("value.deserializer", coderClass);
            props.put("value.serializer", coderClass);
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return props;
    }
}

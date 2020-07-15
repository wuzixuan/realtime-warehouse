package com.zixuan.kafka.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtils {

    //读取Properties文件
    public Properties loadProducerProp(Properties props,String encoderClass){
        InputStream fis = this.getClass().getClassLoader().getResourceAsStream("producer.properties");
        try {
            props.load(fis);
            props.put("value.serializer", encoderClass);
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return props;
    }
}

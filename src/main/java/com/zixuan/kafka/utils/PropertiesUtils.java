package com.zixuan.kafka.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtils {

    //读取Properties文件
    public Properties loadProp(Properties props,String propsName,String encoderClass){
        InputStream fis = this.getClass().getClassLoader().getResourceAsStream(propsName);
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

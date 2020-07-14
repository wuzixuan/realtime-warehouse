package com.zixuan.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class OrderProducer {
    public static void main(String[] args) {

    }

    public void producer(){
        Properties props = new Properties();
        InputStream fis = this.getClass().getClassLoader().getResourceAsStream("/conf/kafka.properties");
        try {
            props.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

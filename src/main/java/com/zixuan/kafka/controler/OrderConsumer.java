package com.zixuan.kafka.controler;

import com.zixuan.kafka.utils.PropertiesUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Properties;

public class OrderConsumer {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties = new PropertiesUtils().loadProp(properties, "comsumer.properties", "com.zixuan.kafka.encoder.ObjectDecoder");
        KafkaConsumer<String, Object> consumer = new KafkaConsumer<String, Object>(properties);
        while (true){
            ConsumerRecords<String, Object> poll = consumer.poll(100);
            for (ConsumerRecord<String, Object> records : poll) {
                System.out.println(records.value().toString());
            }
        }
    }
}

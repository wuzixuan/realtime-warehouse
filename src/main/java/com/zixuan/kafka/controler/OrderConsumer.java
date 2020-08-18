package com.zixuan.kafka.controler;

import com.zixuan.kafka.utils.PropertiesUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

public class OrderConsumer {
    public static void main(String[] args) {
        //创建Properties
        Properties properties = new Properties();
        //加载Properties文件
        properties = new PropertiesUtils().loadConsumerProp(properties, "com.zixuan.kafka.encoder.ObjectDecoder");
        //根据Properties创建kafka消费者对象
        KafkaConsumer<String, Object> consumer = new KafkaConsumer<String, Object>(properties);
        //指定消费的topic
        consumer.subscribe(Arrays.asList("test"));
        while (true){
            ConsumerRecords<String, Object> poll = consumer.poll(100);
            for (ConsumerRecord<String, Object> records : poll) {
                System.out.println(records.value().toString());
            }
        }
    }
}

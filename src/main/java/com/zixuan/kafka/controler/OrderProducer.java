package com.zixuan.kafka.controler;

import com.zixuan.kafka.bean.OrderBean;
import com.zixuan.kafka.utils.PropertiesUtils;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import java.util.Properties;

public class OrderProducer {
    public static void main(String[] args) {

    }

    public void producer(){
        Properties props = new Properties();
        props = new PropertiesUtils().loadProp(props, "kafka.properties","com.zixuan.kafka.encoder.ObjectEncoder");
        KafkaProducer<String, Object> kafkaProducer = new KafkaProducer<String, Object>(props);
        for (int i = 0; i < 100; i++) {
            kafkaProducer.send(new ProducerRecord<String, Object>("test-topic"
                    ,new OrderBean(i,"test",20, 111L)));
        }


    }
}

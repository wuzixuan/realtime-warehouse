package com.zixuan.kafka.controler;

import com.zixuan.baijiaxing.NameUtils;
import com.zixuan.kafka.bean.OrderBean;
import com.zixuan.kafka.utils.DataUtils;
import com.zixuan.kafka.utils.PropertiesUtils;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import java.util.Properties;

public class OrderProducer {
    public static void main(String[] args) {
        Properties props = new Properties();
        props = new PropertiesUtils().loadProducerProp(props, "com.zixuan.kafka.encoder.ObjectEncoder");
        KafkaProducer<String, Object> kafkaProducer = new KafkaProducer<String, Object>(props);
        for (int i = 0; i < 100; i++) {
            kafkaProducer.send(new ProducerRecord<String, Object>("test-topic"
                    , new OrderBean(i, DataUtils.createName(), DataUtils.createDouble(100, "0.00"), DataUtils.createCurrentTimeMillis())));
        }
    }
}

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
        //创建Properties
        Properties props = new Properties();
        //加载Properties文件
        props = new PropertiesUtils().loadProducerProp(props, "com.zixuan.kafka.encoder.ObjectEncoder");
        //根据Properties创建kafka生产者对象
        KafkaProducer<String, Object> kafkaProducer = new KafkaProducer<String, Object>(props);
        //发送数据
        for (int i = 0; i < 100; i++) {
            kafkaProducer.send(new ProducerRecord<String, Object>("test"
                    , new OrderBean(i, DataUtils.createName(), DataUtils.createDouble(100, "0.00"), DataUtils.createCurrentTimeMillis())));
        }
    }
}

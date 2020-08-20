package com.zixuan.kafka.controler;

import com.zixuan.baijiaxing.NameUtils;
import com.zixuan.kafka.bean.OrderBean;
import com.zixuan.kafka.utils.DataUtils;
import com.zixuan.kafka.utils.PropertiesUtils;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.util.Properties;
import java.util.Random;

public class JsonProducer {
    public static void main(String[] args) {
        //创建Properties
        Properties props = new Properties();
        //加载Properties文件
        props = new PropertiesUtils().loadProducerProp(props, "com.zixuan.kafka.encoder.JsonPOJOSerializer");
        //根据Properties创建kafka生产者对象
        KafkaProducer<String, JSONObject> kafkaProducer = new KafkaProducer<String, JSONObject>(props);


        NameUtils nameUtils = new NameUtils();
        //发送数据
        for (int i = 0; i < 100; i++) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("name",nameUtils.getFullName());
                jsonObject.put("age",new Random().nextInt()*10);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            kafkaProducer.send(new ProducerRecord<String, JSONObject>("jsontest", jsonObject));
        }
    }
}

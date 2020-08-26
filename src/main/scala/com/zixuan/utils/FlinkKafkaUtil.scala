package com.zixuan.utils

import java.util.Properties

import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.connectors.kafka.{FlinkKafkaConsumer011}

class FlinkKafkaUtil {

  def getSource(env : StreamExecutionEnvironment,obj : Object,topic : String) ={
    val properties = new Properties()
    val fs = this.getClass.getClassLoader.getResourceAsStream("conf/test.properties")
    properties.load(fs)
    import org.apache.flink.api.scala._
    val dataStream = env.addSource(new FlinkKafkaConsumer011[String](topic, new SimpleStringSchema(), properties))
    dataStream
  }

}
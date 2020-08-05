package com.zixuan.actions

import java.util.Properties

import com.zixuan.kafka.bean.OrderBean
import com.zixuan.kafka.encoder.FlinkKafkaObjectDeserialization
import com.zixuan.utils.FlinkKafkaUtil
import org.apache.flink.contrib.streaming.state.RocksDBStateBackend
import org.apache.flink.streaming.api.CheckpointingMode
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer

class KafkaToKudu {



  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    //设置checkpoint间隔
    env.enableCheckpointing(10000)
    // 设置 exactly-once 模式(默认就是exactly-once)
    env.getCheckpointConfig.setCheckpointingMode(CheckpointingMode.EXACTLY_ONCE)
    // 确保检查点之间有最小间隔为500 ms，假设每10s做一次checkpoint，某次耗时9s，那么正常在本次checkpoint完成后的1s又该做checkpoint了，以下配置可以确保每次checkpoint的最小间隔。
    env.getCheckpointConfig.setMinPauseBetweenCheckpoints(5000)
    //设置状态后端为rockDB
    env.setStateBackend(new RocksDBStateBackend("hdfs://hd01:9000/user/flink/StateBackend"))

    //消费kafka
    val properties = new Properties()
    val fs = this.getClass.getClassLoader.getResourceAsStream("conf/test.properties")
    properties.load(fs)
    import org.apache.flink.api.scala._
    //创建kafka消费者
    val consumer = new FlinkKafkaConsumer[Object]("test-topic",new FlinkKafkaObjectDeserialization,properties)
    //创建流
    val dstream = env.addSource(consumer)
    dstream.print()
  }
}

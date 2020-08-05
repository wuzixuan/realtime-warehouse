package com.zixuan.actions

import java.util.Properties

import com.zixuan.kafka.encoder.FlinkKafkaObjectDeserialization
import org.apache.flink.api.java.utils.ParameterTool
import org.apache.flink.contrib.streaming.state.RocksDBStateBackend
import org.apache.flink.runtime.state.StateBackend
import org.apache.flink.streaming.api.CheckpointingMode
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer

object KafkaToKafka {
  val BOOTSTRAP_SERVERS = "bootstrap.servers"
  val GROUP_ID = "group.id"
  val RETRIES = "retries"
  val TOPIC = "topic"

  def main(args: Array[String]): Unit = {
    val params = ParameterTool.fromArgs(args)

    val env = StreamExecutionEnvironment.getExecutionEnvironment
    env.getConfig.setGlobalJobParameters(params)
    env.enableCheckpointing(10000)
    // 设置 exactly-once 模式(默认就是exactly-once)
    env.getCheckpointConfig.setCheckpointingMode(CheckpointingMode.EXACTLY_ONCE)
    // 确保检查点之间有最小间隔为500 ms，假设每10s做一次checkpoint，某次耗时9s，那么正常在本次checkpoint完成后的1s又该做checkpoint了，以下配置可以确保每次checkpoint的最小间隔。
    env.getCheckpointConfig.setMinPauseBetweenCheckpoints(5000)
    //设置状态后端为rockDB
    //指定rockDB路径，创建RocksDBStateBackend时，需指定泛型，因为env的setStateBackend方法被重写，默认的RocksDBStateBackend类型的方法已被弃用
    val backend:StateBackend = new RocksDBStateBackend("hdfs://hd01:9000/user/flink/StateBackend")
    env.setStateBackend(backend)

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

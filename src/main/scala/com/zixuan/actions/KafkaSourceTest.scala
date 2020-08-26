package com.zixuan.actions

import java.util.Properties

import com.alibaba.fastjson.JSONObject
import com.zixuan.kafka.encoder.{FlinkJsonPOJODeserializer, FlinkJsonPOJOSerializer, FlinkKafkaObjectDeserialization}
import com.zixuan.utils.ParseJsonData
import org.apache.flink.api.common.restartstrategy.RestartStrategies
import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.contrib.streaming.state.RocksDBStateBackend
import org.apache.flink.runtime.state.StateBackend
import org.apache.flink.streaming.api.CheckpointingMode
import org.apache.flink.streaming.api.functions.ProcessFunction
import org.apache.flink.streaming.api.scala.{OutputTag, StreamExecutionEnvironment}
import org.apache.flink.streaming.connectors.kafka.internal.FlinkKafkaProducer
import org.apache.flink.streaming.connectors.kafka.{FlinkKafkaConsumer011, FlinkKafkaProducer011}
import org.apache.flink.util.Collector


object KafkaSourceTest {
  def main(args: Array[String]): Unit = {

    val env = StreamExecutionEnvironment.getExecutionEnvironment
    env.enableCheckpointing(10000)
    // 设置 exactly-once 模式(默认就是exactly-once)
    env.getCheckpointConfig.setCheckpointingMode(CheckpointingMode.EXACTLY_ONCE)
    // 确保检查点之间有最小间隔为500 ms，假设每10s做一次checkpoint，某次耗时9s，那么正常在本次checkpoint完成后的1s又该做checkpoint了，以下配置可以确保每次checkpoint的最小间隔。
    env.getCheckpointConfig.setMinPauseBetweenCheckpoints(5000)
    //设置状态后端为rockDB
    //指定rockDB路径，创建RocksDBStateBackend时，需指定数据类型，因为env的setStateBackend方法被重写，默认返回RocksDBStateBackend类型的方法已被弃用
    val backend:StateBackend = new RocksDBStateBackend("hdfs://kudu1:9000/user/flink/StateBackend",true)
    env.setStateBackend(backend)

    //设置重启策略,重启3次，间隔1分钟
    env.setRestartStrategy(RestartStrategies.fixedDelayRestart(3,60000L))

    val properties = new Properties()
    //获取Properties对象，在配置文件中配置好kafka集群、消费者组、以及其他参数
    val fs = this.getClass.getClassLoader.getResourceAsStream("comsumer.properties")
    properties.load(fs)
    import org.apache.flink.api.scala._
    //创建kafka消费者
    val consumer = new FlinkKafkaConsumer011[Object]("test",new FlinkKafkaObjectDeserialization(),properties)
    //创建流
    val dstream = env.addSource(consumer)
    val obj2JSONObj = dstream.map { obj =>
      val str = ParseJsonData.getJsonString(obj)
      str
    }

    //flink kafka生产者
    //val kafkaProducer = new FlinkKafkaProducer[JSONObject]("kudu1:9092,kudu2:9092,kudu3:9092","jsontest",new FlinkJsonPOJOSerializer[JSONObject]())

    //输出到kafka
    //obj2JSONObj.addSink(kafkaProducer)

    //打印
    obj2JSONObj.print().setParallelism(1)
    obj2JSONObj.print()
    import org.apache.flink.api.scala._

    //将dataStream拆成两份 一份维度表写到hbase 另一份事实表数据写到第二层kafka
    //    val sideOutHbaseTag = new OutputTag[TopicAndValue]("hbaseSinkStream")
    //    val sideOutGreenPlumTag = new OutputTag[TopicAndValue]("greenplumSinkStream")
   val srKafkaTag = new OutputTag[String]("srStream")
   val result = obj2JSONObj.process(new ProcessFunction[String, String] {
     override def processElement(value: String, ctx: ProcessFunction[String, String]#Context, out: Collector[String]): Unit = {
       val amount:BigDecimal = ParseJsonData.getJsonData(value).getBigDecimal("amount")
       if (amount<0){
         //金额小于0，属于退单，发送至侧输出流
         ctx.output(srKafkaTag, value)
       }
       else{
         //正常输出，返回至结果流
         out.collect(value)
       }
     }
   })
   //sr侧输出流得到kafka
   val srKafkaProducer = new FlinkKafkaProducer011[String]("kudu1:9092,kudu2:9092,kudu3:9092","srtest",new SimpleStringSchema)
   //srKafkaProducer.setWriteTimestampToKafka(true)

    result.getSideOutput(srKafkaTag).addSink(srKafkaProducer)
   //ns输出到kafka
    val nsKafkaProducer = new FlinkKafkaProducer011[String]("kudu1:9092,kudu2:9092,kudu3:9092","nstest",new SimpleStringSchema)
    //nsKafkaProducer.setWriteTimestampToKafka(true)
    result.addSink(nsKafkaProducer)

    //执行
    env.execute("test")
  }
}

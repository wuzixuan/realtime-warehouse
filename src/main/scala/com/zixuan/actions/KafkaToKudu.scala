package com.zixuan.actions

import com.zixuan.utils.FlinkKafkaUtil
import org.apache.flink.streaming.api.CheckpointingMode
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment

Object KafkaToKudu {



  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    //设置checkpoint间隔
    env.enableCheckpointing(10000)
    // 设置 exactly-once 模式(默认就是exactly-once)
    env.getCheckpointConfig.setCheckpointingMode(CheckpointingMode.EXACTLY_ONCE)
    // 确保检查点之间有最小间隔为500 ms，假设每10s做一次checkpoint，某次耗时9s，那么正常在本次checkpoint完成后的1s又该做checkpoint了，以下配置可以确保每次checkpoint的最小间隔。
    env.getCheckpointConfig.setMinPauseBetweenCheckpoints(5000)
    //设置状态后端为rockDB

    val flinkKafkaUtil = new FlinkKafkaUtil
    val sourceDStream = flinkKafkaUtil.getSource(env, "test")

  }
}

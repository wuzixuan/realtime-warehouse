package com.zixuan.actions

import org.apache.flink.api.java.utils.ParameterTool

object KafkaToKafka {
  val BOOTSTRAP_SERVERS = "bootstrap.servers"
  val GROUP_ID = "group.id"
  val RETRIES = "retries"
  val TOPIC = "topic"

  def main(args: Array[String]): Unit = {
    val params = ParameterTool.fromArgs(args)


  }
}

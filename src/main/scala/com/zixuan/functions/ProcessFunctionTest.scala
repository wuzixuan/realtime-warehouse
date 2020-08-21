package com.zixuan.functions

import org.apache.flink.streaming.api.functions.ProcessFunction
import org.apache.flink.util.Collector
import org.codehaus.jettison.json.JSONObject

//两个泛型分别为输入数据，输出数据的数据类型
class ProcessFunctionTest extends ProcessFunction[JSONObject,JSONObject]{
  override def processElement(i: JSONObject, context: ProcessFunction[JSONObject, JSONObject]#Context, collector: Collector[JSONObject]): Unit = {
    if(i.getInt("age")==0){

    }
  }
}

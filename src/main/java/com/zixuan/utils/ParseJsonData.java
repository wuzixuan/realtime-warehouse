package com.zixuan.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;


public class ParseJsonData {

    public static JSONObject getJsonData(String data) {
        try {
            return JSONObject.parseObject(data);
        } catch (Exception e) {
            return null;
        }
    }

    public static String getJsonString(Object o) {
        return JSON.toJSONString(o);
    }

    public static JSONObject Obj2Json(String data) {
        try {
            JSONObject.
            return JSONObject.parseObject(data);
        } catch (Exception e) {
            return null;
        }
    }
}

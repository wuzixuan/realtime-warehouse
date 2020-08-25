package com.zixuan.kafka.controler;

import com.zixuan.baijiaxing.NameUtils;
import com.zixuan.kafka.bean.OrderBean;
import com.zixuan.utils.ParseJsonData;

import java.util.Random;

public class Test {
    public static void main(String[] args) {
        Test test = new Test();
        test.jsonTest();
    }


    private void jsonTest(){
        OrderBean bean = new OrderBean(1, "zx", "15.2", 11111l);
        String jsonString = ParseJsonData.getJsonString(bean);
        System.out.println(jsonString);
    }


    private void nameTest(){
        NameUtils nameUtils = new NameUtils();
        String name = null;
        for (int i = 0; i <  2; i++) {
            System.out.println(nameUtils.getFullName());
        }
    }
}

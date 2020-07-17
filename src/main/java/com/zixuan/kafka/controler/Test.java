package com.zixuan.kafka.controler;

import com.zixuan.baijiaxing.NameUtils;

import java.util.Random;

public class Test {
    public static void main(String[] args) {
        NameUtils nameUtils = new NameUtils();
        String name = null;
        for (int i = 0; i <  2; i++) {
            System.out.println(nameUtils.getFullName());
        }
    }
}

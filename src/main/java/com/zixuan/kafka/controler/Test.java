package com.zixuan.kafka.controler;

import com.zixuan.baijiaxing.NameUtils;

import java.util.Random;

public class Test {
    public static void main(String[] args) {
        NameUtils surnameUtils = new NameUtils();
        Random random = new Random();
        String name = null;
        for (int i = 0; i <  200; i++) {
            name = surnameUtils.getSurname()+surnameUtils.getName("gril");
            System.out.println(name);
        }
    }
}

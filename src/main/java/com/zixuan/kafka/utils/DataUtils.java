package com.zixuan.kafka.utils;

import com.zixuan.baijiaxing.NameUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.Random;
import java.util.Timer;

public class DataUtils {
    static  Random rand = new Random();
    static  DecimalFormat format = new DecimalFormat("0.000");
    static NameUtils nameUtils = new NameUtils();
    //获取当前时间
    public static long createCurrentTimeMillis(){
        return System.currentTimeMillis();
    }

    //获取double,参数为最大随机值和数字格式，如"0.00"
    public static String createDouble(int maxValue,String pattern){
        double v = rand.nextDouble() * 100;
        format.applyPattern(pattern);
        return format.format(rand.nextDouble()*maxValue);
    }

    //获取名字
    public static String createName(){
        return nameUtils.getFullName();
    }


}

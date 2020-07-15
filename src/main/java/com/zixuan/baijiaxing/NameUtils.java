package com.zixuan.baijiaxing;

import com.zixuan.utils.DictName;
import com.zixuan.utils.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

public class NameUtils {

    FileUtils fileUtils = new FileUtils();
    HashMap<Integer, String> surnameDict = fileUtils.getDict(DictName.SurnameDict);
    HashMap<Integer, String> boynameDict = fileUtils.getDict(DictName.BoynameDict);
    HashMap<Integer, String> grilnameDict = fileUtils.getDict(DictName.GirlnameDict);
    Random rand = new Random();
    //获取随机姓氏
    public String getSurname(){
        return surnameDict.get(Integer.valueOf(rand.nextInt(100)+1));
    }

    //获取随机名字
    public String getName(String sex){
        //生成名字长度1-2
        int nameLength = rand.nextInt(2) + 1;
        String name = "";

        //根据性别从不同的HashMap中随机读取名字
        if (sex.toLowerCase()=="girl"){
            for (int i = 0; i < nameLength; i++) {
                name = name + grilnameDict.get(Integer.valueOf(rand.nextInt(106)+1));
            }
        }else if (sex.toLowerCase()=="boy"){
            for (int i = 0; i < nameLength; i++) {
                name = name + boynameDict.get(Integer.valueOf(rand.nextInt(205)+1));
            }
        }
        return name;
    }

    //获取全名
    public String getFullName(){
        int isBoy = rand.nextInt(2);
        String sex = null;
        if (isBoy==1){
            sex="boy";
        }else {
            sex="girl";
        }
        String name = null;
        name = this.getSurname()+this.getName(sex);
        return name;

    }
}

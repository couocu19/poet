package com.poets;


import com.alibaba.fastjson.JSON;
import com.poets.pojo.Poets;
import com.poets.util.Big5ToChinese;
import net.minidev.json.JSONUtil;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class Demo {

    public static void main(String[] args) {
        //System.out.println(initChinese());

        int max = 10000;
        int min = 1;
        int ran = (int)(min+Math.random()*(max-min));
        String phone = "18634587269";
        System.out.println(phone.substring(7,11));
        System.out.println(ran);

    }


}

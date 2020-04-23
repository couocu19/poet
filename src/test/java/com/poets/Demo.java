package com.poets;


import com.alibaba.fastjson.JSON;
import com.poets.pojo.Poets;
import com.poets.util.Big5ToChinese;
import com.spreada.utils.chinese.ZHConverter;
import net.minidev.json.JSONUtil;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class Demo {

    public static void main(String[] args) {
        System.out.println(initChinese());


    }


    public static String initChinese(){
        try {

            int flag = -1000;
            String f;
            for(int i =1;i<=58;i++) {
                flag += 1000;
                f = String.valueOf(flag);
                String filePathh = "D:\\poet_json\\chinese-poetry-master\\json\\poet.song." + f + ".json";//json文件地址
                System.out.println(filePathh);

                InputStream inputStream = new FileInputStream(filePathh);
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                StringBuffer sb = new StringBuffer();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                List<Poets> authors = JSON.parseArray(sb.toString(), Poets.class);
                for (Poets a : authors) {
                    a.setTitle(Big5ToChinese.transfer(a.getTitle()));
                    a.setAuthor(Big5ToChinese.transfer(a.getAuthor()));
                    a.setParagraphs(Big5ToChinese.transfer(a.getParagraphs()));
                }
            }

//            }




        } catch (IOException e) {
            e.printStackTrace();
        }
        return "success";
    }





}

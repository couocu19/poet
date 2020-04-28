package com.poets.controller;

import com.alibaba.fastjson.JSON;
import com.poets.dao.AuthorsMapper;
import com.poets.dao.PoetsMapper;
import com.poets.pojo.Authors;
import com.poets.util.Big5ToChinese;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.List;

@RestController
public class JsonController {

    @Autowired
    private AuthorsMapper authorsMapper;

    @Autowired
    private PoetsMapper poetsMapper;

    @GetMapping("/initChinese")
    public String initChinese(){
        try {
//            int flag = -1000;
//            String f;
//            for(int i =1;i<=58;i++) {
//                flag += 1000;
//                f = String.valueOf(flag);
                String filePathh = "D:\\poet_json\\chinese-poetry-master\\json\\authors.tang.json";//json文件地址
               // System.out.println(filePathh);

                InputStream inputStream = new FileInputStream(filePathh);
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                StringBuffer sb = new StringBuffer();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                List<Authors> authors = JSON.parseArray(sb.toString(), Authors.class);
                for (Authors a : authors) {
                    a.setName(Big5ToChinese.transfer(a.getName()));
                    a.setDesc(Big5ToChinese.transfer(a.getDesc()));
                   // System.out.println(a.getDesc());
                    try {
                        authorsMapper.insert(a);
                    } catch (Exception e) {
                        System.out.println(a.getName());
                        continue;
                    }
                }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "success";
    }


}

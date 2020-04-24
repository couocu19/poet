package com.poets.service.impl;

import com.poets.dao.UserMapper;
import com.poets.pojo.User;
import com.poets.service.UserService;
import com.poets.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public boolean register(String memPhone,String password){
        User user = new User();
        //默认初识用户名为手机号
        user.setUsername(memPhone);
        user.setPhone(memPhone);
        String pass = MD5Util.MD5EncodeUtf8(password);
        user.setPassword(pass);

        int rowCount = userMapper.insertSelective(user);
        if(rowCount>0){
            System.out.println("插入成功");
            return true;
        }
        System.out.println("插入失败");
        return false;
    }

    public Map<String,Object> checkLoginByUsername(String username, String password){
        Map<String,Object> map = new HashMap<>();
        String pass = MD5Util.MD5EncodeUtf8(password);
        User user = userMapper.checkLogin(username,pass);
        if(user!=null){
            //登陆成功
            map.put("msg","ok");
            map.put("currentUser",user);
            return map;
        }
        map.put("msg","error");
        return map;
    }

}
package com.poets.service.impl;

import com.poets.dao.UserMapper;
import com.poets.pojo.User;
import com.poets.service.UserService;
import com.poets.util.AccountNumberUtil;
import com.poets.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    //todo:之后补充功能:一个手机号最多绑定两个账号
    public Map<String,Object> register(String memPhone,String password){
        User user = new User();
        //生成用户的账号
        String phoneLast4 = memPhone.substring(7,11);
        String ran = AccountNumberUtil.getRan();
        String account = ran+phoneLast4;
        //如果生成的账号一直重复就不停修改随机数
        while (userMapper.checkUnique(account)>0){
            ran = AccountNumberUtil.getRan();
            account = ran+phoneLast4;
        }
        user.setAccountNumber(account);
        System.out.println(account);
        //初始用户用户名默认为手机号
        user.setName(memPhone);
        user.setPhone(memPhone);
        String pass = MD5Util.MD5EncodeUtf8(password);
        user.setPassword(pass);
        Map<String,Object> map = new HashMap<>();
        int rowCount = userMapper.insertSelective(user);

        if(rowCount>0){
            System.out.println("插入成功");
            user.setPassword(null);
            map.put("msg","ok");
            map.put("accountNumber",account);
            return map;
        }
        System.out.println("插入失败");
        map.put("msg","error") ;
        return map;
    }

    public Map<String,Object> checkLoginByAccountNumber(String accountNumber, String password){
        Map<String,Object> map = new HashMap<>();
        String pass = MD5Util.MD5EncodeUtf8(password);
        User user = userMapper.checkLogin(accountNumber,pass);
        if(user!=null){
            //登陆成功
            user.setPassword(null);
            map.put("msg","ok");
            map.put("currentUser",user);
            return map;
        }
        map.put("msg","error");
        return map;
    }

    public Map<String,Object> update(User user){

        Map<String,Object> map = new HashMap<>();
        int rowCount = userMapper.updateByPrimaryKeySelective(user);
        if(rowCount>0){
            map.put("msg","ok");
            map.put("user",user);
            return map;
        }
        map.put("msg","error");
        return map;

    }

}
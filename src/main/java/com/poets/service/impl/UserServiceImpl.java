package com.poets.service.impl;

import com.poets.dao.ClothesMapper;
import com.poets.dao.UserMapper;
import com.poets.pojo.Clothes;
import com.poets.pojo.User;
import com.poets.service.UserService;
import com.poets.util.AccountNumberUtil;
import com.poets.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import com.poets.RedisAutoConfiguration.*;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("userService")
public class UserServiceImpl implements UserService {

    public static final String CACHE_KEY_USER = "user:";


    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ClothesMapper clothesMapper;

    @Autowired
    private RedisTemplate redisTemplate;



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
            //缓存key
            String key = CACHE_KEY_USER+user.getId();
            System.out.println(key);

            redisTemplate.opsForValue().set(key,user);
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
        //先直接修改数据库
        int rowCount = userMapper.updateByPrimaryKeySelective(user);
        if(rowCount>0){
            //再修改缓存
            //缓存key
            String key = CACHE_KEY_USER+user.getId();
            User current = userMapper.selectByPrimaryKey(user.getId());
            //修改也是用set命令，redis没有update操作，都是重新设置新值
            redisTemplate.opsForValue().set(key,current);

            current.setPassword(null);
            map.put("msg","ok");
            map.put("user",current);
            return map;
        }
        map.put("msg","error");
        return map;

    }

    public Map<String,Object> addClothes(Clothes clothes){
        Map<String,Object> map = new HashMap<>();
        if(!checkExist(clothes)){
            map.put("msg","当前装扮已存在~");
            return map;
        }
        int rowCount = clothesMapper.insertSelective(clothes);
        if(rowCount>0){
            map.put("msg","ok");
            map.put("cloth",clothes);
            return map;
        }
        map.put("msg","error");
        return map;
    }

    private boolean checkExist(Clothes clothes){
        Integer userId = clothes.getUserId();
        Integer hair = clothes.getHair();
        Integer dress = clothes.getDress();
        Integer back = clothes.getBackground();
        Integer face = clothes.getFace();

        Clothes clothes1 = clothesMapper.selectByMap(userId,hair,dress,back,face);
        if(clothes1!=null){
            return false;
        }
        return true;
    }

    public Map<String,Object> deleteClothes(Integer id){
        Map<String,Object> map = new HashMap<>();
        int rowCount = clothesMapper.deleteByPrimaryKey(id);
        if(rowCount>0){
            map.put("msg","ok") ;
            return map;
        }

        map.put("msg","error");
        return map;
    }

}
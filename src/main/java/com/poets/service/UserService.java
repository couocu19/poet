package com.poets.service;

import com.poets.pojo.User;

import java.util.Map;

public interface UserService {
    public  Map<String,Object> register(String memPhone,String password);
    public Map<String,Object> checkLoginByAccountNumber(String username, String password);

    public Map<String,Object> update(User user);


}

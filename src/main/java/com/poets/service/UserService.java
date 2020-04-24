package com.poets.service;

import java.util.Map;

public interface UserService {
    public boolean register(String memPhone,String password);
    public Map<String,Object> checkLoginByUsername(String username, String password);
}

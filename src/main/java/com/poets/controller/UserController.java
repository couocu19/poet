package com.poets.controller;


import com.alibaba.fastjson.JSONObject;
import com.poets.pojo.User;
import com.poets.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CodeController codeController;

    @RequestMapping("/register.do")
    public Map<String,Object> register(String memPhone, String code, String password, HttpSession session) {
        JSONObject json = (JSONObject) session.getAttribute("code");
        Map map = new HashMap();
        System.out.println(json.getString("memPhone"));
        System.out.println(json.getString("code"));

        System.out.println(memPhone);
        System.out.println(code);
        if (memPhone .equals(json.getString("memPhone") )&& code.equals(json.getString("code"))) {
            Boolean result =  userService.register(memPhone,password);
            if (result == true) {
                System.out.println("1");
                map.put("msg", "ok");
                return map;
            }
        }
        map.put("msg", "error");

        return map;
    }


    @RequestMapping("/login.do")
    public Map<String,Object> loginByUserName(String username,String password,HttpSession httpSession){
        Map result = userService .checkLoginByUsername(username,password);
        if(result.get("msg") == "ok"){
            User user = (User) result.get("currentUser");
            httpSession.setAttribute("currentUser",user);
        }
        return result;
    }

    @RequestMapping("/logout.do")
    public String logout(HttpSession httpSession){
        User user = (User)httpSession.getAttribute("currentUser");
        if(user == null){
            return "当前未登录";
        }
        httpSession.setAttribute("currentUser",null);
        return "ok";
    }




}

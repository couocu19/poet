package com.poets.controller;


import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@Api(tags = "UserOauthController")
//@RequestMapping("/user_oauth")
public class OauthController {


//    @PostMapping("/login")
//    public String index(String username,String password){
//        return "index";
//    }

    //登录成功之后的首页
    @GetMapping("/index")
    public String index(){
        return "index";
    }



    //日志管理
    @GetMapping("/syslog")
    public String showOrder(){
        return "syslog";
    }

    //用户管理
    @GetMapping("/sysuser")
    public String addOrder(){
        return "sysuser";
    }

    //具体业务2
    @RequestMapping("/bizi1")
    public String updateOrder(){
        return "bizi1";
    }

    //具体业务1
    @GetMapping("/bizi2")
    public String deleteOrder(){
        return "bizi2";
    }







}

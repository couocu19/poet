package com.poets.controller;

import com.poets.pojo.User;
import com.poets.service.PoetService;
import com.poets.service.UserService;
import com.poets.util.AccountNumberUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/poet")
public class PoetController {
    @Autowired
    private UserService userService;
    @Autowired
    private PoetService poetService;


    @RequestMapping("/ran_share.do")
    @ApiOperation(value = "每日分享", notes = "每次刷新获取新的诗词一首")
    public Map<String,Object> randomShare(){
        int ran = AccountNumberUtil.getRanNum();
        return poetService.ranShare(ran);

    }

    public Map<String,Object> selectByKeyWords(String key, HttpSession session){
        Map<String,Object> map = new HashMap<>();
        User user = (User)session.getAttribute("currentUser");
        if(user == null){
            map.put("msg","当前用户未登录");
        }

        



    }

}

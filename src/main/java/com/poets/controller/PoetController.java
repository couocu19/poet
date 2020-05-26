package com.poets.controller;

import com.poets.pojo.User;
import com.poets.service.PoetService;
import com.poets.service.UserService;
import com.poets.util.AccountNumberUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

//todo:用户升级功能实现
@RestController
@Api(tags = "PoetController")
@RequestMapping("/poet")
public class PoetController {
    @Autowired
    private UserService userService;
    @Autowired
    private PoetService poetService;

    @RequestMapping("/ran_share.do")
    @ApiOperation(value = "每日分享", notes = "每次刷新获取新的诗词一首")
    public Map<String,Object> randomShare(HttpSession session){
        User user = (User) session.getAttribute("currentUser");
        Integer uid;
        if(user == null){
            uid = 0;
        }else{
            uid = user.getId();
        }
        int ran = AccountNumberUtil.getRanNum();
        return poetService.ranShare(ran,uid);

    }

    @RequestMapping("/select.do")
    @ApiOperation(value = "诗词/诗人查询", notes = "根据关键字模糊查询")
    public Map<String,Object> selectByKeyWords(String key,HttpSession session){
        User user = (User) session.getAttribute("currentUser");
        Integer uid;
        if(user == null){
            uid = 0;
        }else{
            uid = user.getId();
        }
        System.out.println(uid);
        return poetService.selectByKey(key,uid);
    }

    @RequestMapping("/get_author.do")
    @ApiOperation(value = "查看诗人信息", notes = "根据id查看诗人")
    public Map<String,Object> getAuthor(Integer id){
        return poetService.getAuthor(id);
    }

    @RequestMapping("/get_poet.do")
    @ApiOperation(value = "查看诗词信息", notes = "根据id查看诗词信息")
    public Map<String,Object> getPoet(Integer id,HttpSession session){
        User user = (User) session.getAttribute("currentUser");
        Integer uid;
        if(user == null){
            uid = 0;
        }else{
            uid = user.getId();
        }

        return poetService.getPoet(id,uid);

    }

    //todo:飞花令游戏

}

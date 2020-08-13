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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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




    @RequestMapping("/ran_shareList.do")
    @ApiOperation(value = "每日分享列表", notes = "每次刷新随机获取5首诗词")
    public Map<String,Object> ranShareList(HttpSession session){
        Map<String,Object> map = new HashMap<>();
        User user = (User) session.getAttribute("currentUser");
        if(user == null){
            map.put("msg","need-login");
            return map;
        }
        List<Integer> idList = new ArrayList<>();
        Integer id = null;
        for(int i =0;i<5;i++){
            id = AccountNumberUtil.getRanNum();
            idList.add(id);
        }
        return poetService.ranShareList(idList,user.getId());
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

    @RequestMapping("/get_as_poet.do")
    @ApiOperation(value = "根据年龄性别推荐诗词", notes = "根据年龄性别推荐诗词")
    public Map<String,Object> getAsPoet(Integer age,String sex,HttpSession session){
        Map<String,Object> map = new HashMap<>();
        User user = (User) session.getAttribute("currentUser");
        if(user == null){
            map.put("msg","用户未登录");
            return  map;
        }

        return poetService.getAsPoet(age,sex);

    }

}

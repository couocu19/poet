package com.poets.controller;


import com.poets.dao.RememberedMapper;
import com.poets.dao.UserMapper;
import com.poets.pojo.Remembered;
import com.poets.pojo.User;
import com.poets.service.CollectService;
import com.poets.service.PoetService;
import com.poets.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 收藏 取消收藏
 * 背诵 取消背诵
 */
@RestController
@RequestMapping("/collect")
public class CollectController {

    @Autowired
    private UserService userService;
    @Autowired
    private PoetService poetService;
    @Autowired
    private CollectService collectService;

    @Autowired
    private RememberedMapper rememberedMapper;
    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/collect.do")
    @ApiOperation(value = "收藏/取消收藏",notes = "对于一个诗词，第一次点即为收藏，第二次点即为取消收藏")
    public Map<String,Object> collectPoet(HttpSession session, Integer id){
        Map<String,Object> map = new HashMap<>();
        User user = (User)session.getAttribute("currentUser");
        if(user == null){
            map.put("msg","error");
            return map;
        }
        Integer user_id = user.getId();
        return collectService.collect(id,user_id);
    }


    @RequestMapping("/remember.do")
    @ApiOperation(value = "背诵诗词",notes = "用户添加收藏列表中的诗词到背诵列表")
    public Map<String,Object> rememberPoet(HttpSession session,Integer poetId){
        Map<String,Object> map = new HashMap<>();
        User user = (User)session.getAttribute("currentUser");
        if(user == null){
            map.put("msg","当前未登录~");
            return map;
        }
        Integer userId = user.getId();
        map = collectService.remember(poetId,userId);

        int grade = user.getGrades();
        System.out.println(grade);
        int newGrade = getGrades(improve(userId));
        System.out.println(newGrade);
        if(grade!=newGrade){
            user.setGrades(newGrade);
            int row = userMapper.updateByPrimaryKeySelective(user);
            if(row>0){
                map.put("升级",newGrade);
            }
        }
        return map;
    }

    //获取当前用户所背诵过的诗词总数
    private int improve(int id){
        List<Remembered> list = rememberedMapper.selectByUserId(id);
        System.out.println(list.size());
        return list.size();
    }

    //计算出当前用户的等级
    private int getGrades(int sum){
        if(sum == 0)
            return 1;
        int s = sum*5;
        int a;
        int i;
        for(i =1;;i++){
            a = 5*i*(i+1);
            if(s==a) {
                i++;
                break;
            }
            if(a>s)
                break;
        }
        return i;
    }

    @RequestMapping("/cancelRemember.do")
    @ApiOperation(value = "取消背诵诗词",notes = "用户取消背诵诗词并还原到收藏列表")
    public Map<String,Object> cancelRemember(HttpSession session,Integer poetId){
        Map<String,Object> map = new HashMap<>();
        User user = (User)session.getAttribute("currentUser");
        if(user == null){
            map.put("msg","error");
            return map;
        }
        Integer userId = user.getId();
        return collectService.cancelRemember(poetId,userId);
    }


    @RequestMapping("/getCollects.do")
    @ApiOperation(value = "获取收藏列表",notes = "获取用户收藏的诗词列表")
    public Map<String,Object> getCollects(HttpSession session){
        Map<String,Object> map = new HashMap<>();
        User user = (User)session.getAttribute("currentUser");
        if(user == null){
            map.put("msg","error");
            return map;
        }
        Integer id = user.getId();
        return collectService.getCollects(id);
    }

    @RequestMapping("/getRemember.do")
    @ApiOperation(value = "获取背诵列表",notes = "获取用户背诵的诗词列表")
    public Map<String,Object> getRemembers(HttpSession session){
        Map<String,Object> map = new HashMap<>();
        User user = (User)session.getAttribute("currentUser");
        if(user == null){
            map.put("msg","error");
            return map;
        }
        Integer id = user.getId();
        return collectService.getRemembers(id);
    }

}

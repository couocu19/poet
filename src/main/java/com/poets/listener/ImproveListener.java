package com.poets.listener;

import com.poets.dao.RememberedMapper;
import com.poets.dao.UserMapper;
import com.poets.pojo.Remembered;
import com.poets.pojo.User;
import com.poets.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.List;

@WebListener
public class ImproveListener implements HttpSessionListener {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RememberedMapper rememberedMapper;

    public synchronized void sessionCreated(HttpSessionEvent arg0) {

        System.out.println("加载11111");
        HttpSession session = arg0.getSession();
        System.out.println(session.getId());
    }

    public synchronized void sessionDestroyed(HttpSessionEvent arg0) {//监听session的撤销
        System.out.println("当前监听结束");
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
            if(s<=a)
                break;
        }
        return i;
    }
}

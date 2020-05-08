package com.poets.listener;

import com.poets.dao.RememberedMapper;
import com.poets.dao.UserMapper;
import com.poets.pojo.Remembered;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.List;

@WebListener
public class ImproveListener implements HttpSessionListener {

    public synchronized void sessionCreated(HttpSessionEvent arg0) {

        System.out.println("加载11111");
        HttpSession session = arg0.getSession();
        System.out.println(session.getId());
    }

    public synchronized void sessionDestroyed(HttpSessionEvent arg0) {//监听session的撤销
        System.out.println("当前监听结束");
    }


}

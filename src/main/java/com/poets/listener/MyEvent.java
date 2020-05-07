package com.poets.listener;

import com.poets.pojo.User;
import org.springframework.context.ApplicationEvent;

import javax.servlet.http.HttpSession;

public class MyEvent extends ApplicationEvent {

    private HttpSession session;
    public MyEvent(Object source,HttpSession session){
        super(source);
        this.session = session;
    }

    public int getId(){
        User user = (User)session.getAttribute("currentUser");
        if(user == null)
            return -1;
        else
            return user.getId();
    }

}

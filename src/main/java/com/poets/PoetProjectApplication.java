package com.poets;

import com.poets.listener.ImproveListener;
import com.poets.listener.MyEvent;
import javafx.application.Application;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ConfigurableApplicationContext;

import javax.servlet.http.HttpSession;

@ServletComponentScan
@SpringBootApplication(scanBasePackages = "com.poets")
@MapperScan(basePackages = "com.poets.dao")
public class PoetProjectApplication {
	public static void main(String[] args) {
		SpringApplication.run(PoetProjectApplication.class, args);

//		SpringApplication application = new SpringApplication(PoetProjectApplication.class);
//		//添加Listener
//		application.addListeners(new ImproveListener());
//		ConfigurableApplicationContext context = application.run(args);
//		HttpSession session =
//		//发布事件，调用就是AbstractApplicationContext的publishEvent()方法
//		context.publishEvent(new MyEvent("升级功能开始监听",));
//		context.close();

	}

}

package com.poets;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication(scanBasePackages = "com.poets")
@MapperScan(basePackages = "com.poets.dao")
public class PoetProjectApplication {
	public static void main(String[] args) {
		SpringApplication.run(PoetProjectApplication.class, args);
	}

}

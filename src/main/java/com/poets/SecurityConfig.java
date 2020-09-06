package com.poets;

import org.springframework.boot.autoconfigure.security.servlet.SpringBootWebSecurityConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.swing.*;

@Configuration
public class SecurityConfig  extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //规则：所有请求必须都在登录之后访问
        //配置了之后，在访问login.html页面时，会弹出一个授权认证的框，需要输入提供的用户名和密码才可以进入到项目的登录中。
        //这个认证的框并不是自己开发的，而是spring-security提供的
        //
       // http.httpBasic().and().authorizeRequests().anyRequest().authenticated();

        //跨站防御攻击
        http.csrf().disable().
                formLogin()
                  .loginPage("/login.html")
                  .loginProcessingUrl("/login")
                  .defaultSuccessUrl("/index")
                .and()
                .authorizeRequests()
                   .antMatchers("/login.html","/login").permitAll()
                   .antMatchers("/biz1","/biz2")
                     .hasAnyAuthority("ROLE_user","ROLE_admin")
                   .antMatchers("/syslog","/sysuser")
                     .hasAnyAuthority("ROLE_admin")
                   .anyRequest().authenticated();
    }


    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user")
                .password(passwordEncoder().encode("123456"))
                .roles("user")
                    .and()
                .withUser("admin")
                .password(passwordEncoder().encode("123456"))
                .roles("admin")
                   .and()
                .passwordEncoder(passwordEncoder());
    }

    //对用户名和密码进行加密
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public void configure(WebSecurity web){
        //将项目中的静态资源开放
        web.ignoring()
                .antMatchers("/css/**","/fonts/**","/img/**","/js/**");
    }
}

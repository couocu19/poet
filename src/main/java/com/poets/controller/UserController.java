package com.poets.controller;


import com.alibaba.fastjson.JSONObject;
import com.poets.pojo.User;
import com.poets.service.UserService;
import com.poets.util.QiniuUploadImageUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(tags = "UserController")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CodeController codeController;

    @ApiOperation(value = "注册账号", notes = "根据手机号获取验证码以及设置的密码注册")
    @RequestMapping("/register.do")
    public Map<String, Object> register(String memPhone, String code, String password, HttpSession session) {
        JSONObject json = (JSONObject) session.getAttribute("code");
        Map map = new HashMap();
        System.out.println(json.getString("memPhone"));
        System.out.println(json.getString("code"));

        if (memPhone.equals(json.getString("memPhone")) && code.equals(json.getString("code"))) {
            return userService.register(memPhone, password);
        }
        map.put("msg", "error");
        return map;
    }

    @ApiOperation(value = "登录账号", notes = "用户通过账号密码登录账号")
    @RequestMapping("/login.do")
    public Map<String, Object> loginByUserName(String accountNumber, String password, HttpSession httpSession) {
        Map result = userService.checkLoginByAccountNumber(accountNumber, password);
        if (result.get("msg") == "ok") {
            User user = (User) result.get("currentUser");
            httpSession.setAttribute("currentUser", user);
        }
        return result;
    }


    @ApiOperation(value = "用户登出账号", notes = "用户退出登录")
    @RequestMapping("/logout.do")
    public String logout(HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("currentUser");
        if (user == null) {
            return "当前未登录";
        }
        httpSession.setAttribute("currentUser", null);
        return "ok";
    }

    @ApiOperation(value = "修改个人信息", notes = "用户修改用户名/个性签名")
    @RequestMapping("/update.do")
    public Map<String, Object> update(User user, HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) {
            map.put("msg", "当前用户未登录");
            return map;
        }
        //用户的id和账号不可以修改,头像需要单独修改噢,绑定的手机号也需要单独修改
        //可以修改的内容有：用户名,个性签名
        user.setId(currentUser.getId());
        user.setAccountNumber(currentUser.getAccountNumber());
        user.setPhone(currentUser.getPhone());
        return userService.update(user);
    }

    @ApiOperation(value = "修改个人信息", notes = "用户修改用户名/个性签名")
    @RequestMapping("/upload_header.do")
    public Map<String, Object> uploadHeader(MultipartFile file, HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) {
            map.put("msg", "当前用户未登录");
            return map;
        }
        if (file == null) {
            map.put("msg", "null-image");
            return map;
        }
        String fileUrl = uploadImage(file, session);
        if (fileUrl.equals("error")) {
            map.put("msg", "error-file");
            return map;
        }
        currentUser.setHeader(fileUrl);
        //将图像的路径保存到数据库
        User user = new User();
        //记得要有id
        user.setId(currentUser.getId());
        user.setHeader(fileUrl);
        return userService.update(user);
    }



    private String uploadImage(MultipartFile file, HttpSession session) {
        String fileName = file.getOriginalFilename();
        String newFileNames = null;
        //截取文件扩展名
        String url = null;
        try {
            String path = session.getServletContext().getRealPath("/") + fileName;
            System.out.println(path);
            file.transferTo(new File(path));
            url = QiniuUploadImageUtil.fileUpload(path);
            System.out.println(url);
        } catch (Exception e) {
            System.out.println("error");
            return "error";
        }
        return url;
    }




}

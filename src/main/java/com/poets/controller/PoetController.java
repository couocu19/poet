package com.poets.controller;

import com.poets.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/poet")
public class PoetController {
    private UserService userService;



}

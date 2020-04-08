package com.item.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 跳转页面控制器
 */
@Controller
public class SkipPageControl {

    @RequestMapping("chatroom")
    public String chatroom(){
        return "chatroom";
    }

    @RequestMapping("login")
    public String login(){
        return "index";
    }

    @RequestMapping("homePage")
    public String homePage(){
        return "homepage";
    }
}

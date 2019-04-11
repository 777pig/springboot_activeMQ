package com.example.springboot_active.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Text2 {

    @RequestMapping("/index")
    public String get(){
        System.out.println("访问");
        return "index";
    }
    @RequestMapping("/login")
    public String list(@RequestParam String user, ModelAndView mode){
        System.out.println("访问列表页");

        mode.addObject("loginuser",user);
        return "list";
    }

}
